package project.modules;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.NoSuchElementException;
import java.util.Scanner;

public class IO implements IIO { // Input/Output

    private static String[] stringArray;
    private static IO tmp = new IO();

    // Takes in 1 or 2 Strings one being the filePath the other being what type ie. write to tickets or read
    // for static calls
    public static void callMe(String...input) throws IOException{
        if (input.length < 2)
            tmp.writeToFile(input[0]);
        else tmp.readFile(input[0], input[1]);
    }

    @Override // reads both tickets.txt and cinema.txt
    public void readFile(String type, String filePath) {
        try {
            Scanner cinema = new Scanner(new File(filePath));

            //Chooses delimiter depending on which file due to different formatting
            cinema.useDelimiter(type.equals("Ticket") ? "/" : "\n");

            while (cinema.hasNext()) {
                String string = cinema.next();
                stringArray = string.split(";");
                createObject(stringArray);
            }
            cinema.close();
        } catch (Exception e) { e.printStackTrace(); }
    }

    @Override // creates objects for each line in cinema.txt and existing tickets
    public void createObject(String[] i) throws NoSuchElementException, IOException {
        switch (i[0].replaceAll("\\d", "")) {
            case "theatre":
                new Theatre(i[1], Integer.parseInt(i[2]));
                break;
            case "movie":
                new Movie(i[1], Integer.parseInt(i[3]));
                break;
            case "screening": 
                new Screening(Movie.getMovie(i[1]), Integer.parseInt(i[2]), Theatre.getTheatre(i[3]));
                break;
            default:
                Movie movie = Movie.getMovie(i[0]);
                int seats = new ArrayList<>(Arrays.asList(i[2].split(","))).size();
                new Tickets(movie, Screening.findScreening(movie, i[1]), seats, true, i[3]);
        }
    }

    @Override // writes new tickets to tickets.txt
    public void writeToFile(String input) throws IOException {
        try (BufferedWriter txt = new BufferedWriter(new FileWriter("src/main/resources/textfiles/tickets.txt", true))) {
            txt.write(input.toString());
        } 
        catch (FileNotFoundException e) { 
            e.printStackTrace(); 
        }
    }
}
