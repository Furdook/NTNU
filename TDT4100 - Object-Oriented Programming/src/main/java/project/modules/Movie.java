package project.modules;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class Movie {
    private int length;
    private String title;
    private List<Screening> screenings = new ArrayList<>();
    private static List<Movie> movies = new ArrayList<>();

    public Movie(String title, int length) {
        if (!title.equals("")) this.title = title;
        else throw new IllegalArgumentException("Title can not be null");

        if (length > 0) this.length = length;
        else throw new IllegalArgumentException("Length can not be less than zero");

        movies.add(this);
    }

    public static Movie getMovie(String input) {
        try { // Finds movie based on its title
            Optional<Movie> tmp = movies.stream().filter(p -> p.getTitle().equals(input)).findAny();
            return tmp.get();
        } catch (Exception e) { return null; }
    }

    public static List<Movie> getMovies() {
        return movies;
    }

    public String getTitle() {
        return this.title;
    }

    public int getLength() { // Not needed for the program, but used in tests to check correct length
        return this.length;
    }

    public void setScreenings(Screening screening) {
        screenings.add(screening);
    }

    public List<Screening> getScreenings() {
        return this.screenings;
    }
}
