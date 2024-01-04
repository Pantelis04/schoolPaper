package auth.cableTv.domain;

import java.util.ArrayList;
import java.util.List;


public class Movie extends Media {

    private int duration;
    private List<String> relatedMovies = new ArrayList<>();

    public Movie() {
    }


    public Movie(String title, String genre, String description, boolean suitability, int releaseYear, List<String> actors, int duration, List<String> relatedMovies) {
        super(title, genre, description, suitability, releaseYear, actors);
        this.duration = duration;
        this.relatedMovies = relatedMovies;
    }


    @Override
    public String toString() {
        return "Movie(title=" + getTitle() + ", description=" + getDescription() + ", suitability=" + isSuitability() + ", releaseYear=" + getReleaseYear() + ", actors=" + getActors() + ", duration=" + this.duration + ", genre=" + getGenre() + ", relatedMovies=" + this.relatedMovies + ")";
    }

    public int getDuration() {
        return duration;
    }

    public List<String> getRelatedMovies() {
        return relatedMovies;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public void setRelatedMovies(List<String> relatedMovies) {
        this.relatedMovies = relatedMovies;
    }
}
