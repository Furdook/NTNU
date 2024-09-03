package project.modules;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Tickets {
    private Movie movie;
    private String name;
    private Screening screening;
    private List<String> seating = new ArrayList<>();
    private static List<Tickets> tickets = new ArrayList<>();
    
    public Tickets(Movie movie, Screening screening, int seats, boolean exist, String name) throws IOException {
        if (checkTicket(screening.toString(), movie.getTitle(), name)) {

            /* Null errors and wrong int prevented in Controller!
            'if not -> throw' causes dead code errors 
            will throw null error if neccesary */
            this.screening = screening;
            this.seating = this.screening.setSeats(seats);
            this.name = name;
            this.movie = movie;

            if (!exist) // For existing tickets in tickets.txt on startup
                IO.callMe(this.movie.getTitle()+";"+screening.getTime()+";"+getSeats()+";"+name+"/");

            tickets.add(this);
        }
        else throw new IllegalArgumentException("Ticket already exists");
    }

    // Checks if ticket already exists in tickets array
    public static Boolean checkTicket(String input, String movie, String name) {
        if (!tickets.stream().anyMatch(p -> p.getScreening().equals(input) && p.getMovie().equals(movie) && p.name.equals(name))) return true;
        else return false;
    }

    public String getScreening() {
        return this.screening.toString();
    }

    public String getMovie() {
        return this.movie.getTitle();
    }

    public List<String> getSeats() {
        return this.seating;
    }

    public static List<Tickets> getTickets() {
        return tickets;
    }

    @Override
    public String toString() { // a mess
        return (this.name + ": " +this.screening.getMovie().getTitle() + "\n" + this.screening.getTheatre() + ": " + getScreening() + "\n" + getSeats() + "\n\n").replaceAll("\\[", "").replaceAll("\\]", "");
    }
}
