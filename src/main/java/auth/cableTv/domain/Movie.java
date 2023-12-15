package auth.cableTv.domain;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Movie extends Media {

    private int duration;
    private String genre;
    private List<String> actors;
    private List<String> relatedMovies;


    public Movie(int duration, String genre, List<String> actors, List<String> relatedMovies) {
        this.duration = duration;
        this.genre = genre;
        this.actors = actors;
        this.relatedMovies = relatedMovies;
    }

    public Movie(int id, String title, String description, boolean suitability, int releaseYear, int duration, String genre, List<String> actors, List<String> relatedMovies) {
        super(id, title, description, suitability, releaseYear);
        this.duration = duration;
        this.genre = genre;
        this.actors = actors;
        this.relatedMovies = relatedMovies;
    }
}
