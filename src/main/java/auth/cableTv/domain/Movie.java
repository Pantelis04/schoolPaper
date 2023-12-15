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
    private List<String> relatedMovies;

    public Movie(int id, String title, String description, boolean suitability, int releaseYear, List<String> actors, int duration, String genre, List<String> relatedMovies) {
        super(id, title, description, suitability, releaseYear, actors);
        this.duration = duration;
        this.genre = genre;
        this.relatedMovies = relatedMovies;
    }
}
