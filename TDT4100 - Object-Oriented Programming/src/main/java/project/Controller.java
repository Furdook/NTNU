package project;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;
import project.modules.Movie;
import project.modules.Screening;
import project.modules.Tickets;

public class Controller implements Initializable {

    @FXML
    private Button screening1, screening2, screening3, screening4, film1, film2, film3, film4, ticketBtn;

    @FXML
    private Text title;

    @FXML
    private TextArea ticketText;

    @FXML
    private TextField textInput, nameInput;

    // For the variable user interface 
    @FXML
    private static String movie, screening, screeningButton, lastPage;

    @FXML
    private static Integer inti; // number of seats from seating.fxml

    @FXML 
    private static String name;

    @FXML
    private void swapPage(ActionEvent e) throws IOException {
        lastPage = App.getScene();
        Button button = (Button) e.getSource();
        App.changeScene(button.getId());
    }

    @FXML
    private void pickMovie(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        movie = button.getText();
        App.changeScene("Screenings");
    }

    @FXML
    private void pickScreening(ActionEvent e) throws IOException {
        Button button = (Button) e.getSource();
        screening = button.getText();
        screeningButton = button.getId();
        App.changeScene("Seating");
    }

    @Override
    public void initialize(URL arg0, ResourceBundle arg1) throws NumberFormatException { // Controls variable button names etc.
        switch (App.getScene()) {
            case "Movies":
                List<Movie> movies = Movie.getMovies();
                List<Button> btns = Arrays.asList(film1, film2, film3, film4);

                int i = 0; // set the movies based on created objects
                for (Button button : btns) {
                    button.setText(movies.get(i).getTitle());
                    i++;
                }
                break;

            case "Screenings":
                List<Screening> screenings = Movie.getMovie(movie).getScreenings();
                List<Button> btns2 = Arrays.asList(screening1, screening2, screening3, screening4);
                
                int j = 0; // set the correct text for which screenings belong to chosen movie
                for (Button button : btns2) {
                    button.setText(screenings.get(j).toString());
                    j++;
                }
                title.setText(movie);
                break;

            case "Seating":
                title.setText(movie);

                nameInput.setOnAction(new EventHandler<ActionEvent>() { // on Enter pressed

                    @Override
                    public void handle(ActionEvent arg0) {
                        try { // For user to pick number of seats and pick their name
                            if (!Double.isNaN(Integer.parseInt(textInput.getText()))) { // check if value is a number
                                if (getScreening().getSeats().size() > Integer.parseInt(textInput.getText())) { // check if there are enough seats
                                    if (nameInput.getText().equals("")) { ticketBtn.setText("You need a name"); }
                                    else { 
                                        inti = Integer.parseInt(textInput.getText());
                                        name = nameInput.getText().toString();
                                        
                                        if (Tickets.checkTicket(screening, movie, name))
                                        {
                                            ticketBtn.setText("See ticket");
                                            ticketBtn.setDisable(false);
                                        }
                                        else {
                                            ticketBtn.setText("You already have a ticket to this screening");
                                            ticketBtn.setDisable(true);
                                        }
                                    }
                                }
                                else {
                                    ticketBtn.setText("Not enough available seats");
                                    ticketBtn.setDisable(true);
                                }
                            }
                        }
                        catch (Exception e) { ticketBtn.setDisable(true); }
                    }
                });
                break;

            case "ticketBtn": 
                if (!lastPage.equals("Movies")) {  // only create a new ticket if a new one is made
                     try {
                        new Tickets(Movie.getMovie(movie), getScreening(), inti, false, name);
                    } catch (IOException e) { e.printStackTrace(); }
                }
                ticketText.setText(Tickets.getTickets().toString());
                break;
        }
    }

    private Screening getScreening() { // used twice in Controller, could've been placed in Screening.java
        return Movie.getMovie(movie).getScreenings()
                    .get(Integer
                    .parseInt(screeningButton
                    .split("(?<=\\D)(?=\\d)")[1]
                    .toString())-1);
    }
}
