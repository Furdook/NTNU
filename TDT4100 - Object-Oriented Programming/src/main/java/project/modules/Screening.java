package project.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

public class Screening { 
    private List<String> seating = new ArrayList<>();
    private Theatre theatre;
    private String time;
    private Movie movie;
  
    public Screening(Movie movie, int start, Theatre theatre) {
        this.seating = theatre.getSeatList();

        theatre.setTaken(start, movie.getLength());
        setTime(start, movie.getLength());

        this.movie = movie;
        this.theatre = theatre;

        movie.setScreenings(this); 
    }

    public void setTime(int start, int length) { // surely a better way to do this built in Date?
        start *= 15;
        int hour = (start/60)+12;
        int min = start%60;

        length *= 15;
        length = start+length;
        int hour2 = (length/60)+12;
        int min2 = length%60;

        this.time = String.format("%d:%02d", hour, min) + " - " + String.format("%d:%02d", hour2, min2);
    }

    // find screening based on which film and what time as a string ie. '17:00-19:30'
    public static Screening findScreening(Movie movie, String screening) throws NoSuchElementException {
        Optional<Screening> tmp = movie.getScreenings().stream().filter(p -> p.toString().equals(screening)).findFirst();
        Screening temp = tmp.get();
        return temp; 
    }

    public List<String> setSeats(int a) {
        List<String> temp = new ArrayList<>();
        temp.addAll(this.seating.subList(0, a));
        this.seating.subList(0, a).clear();
        return temp;
    }

    public List<String> getSeats() { 
        return this.seating;
    }

    public Movie getMovie() {
        return this.movie;
    }

    public String getTime() {
        return this.time;
    }

    public String getTheatre() {
        return this.theatre.getName();
    }

    @Override
    public String toString() {
        return getTime();
    }
}
