package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class Movie extends Media {

    private int duration;
    private String genre;
    private List<String> relatedMovies= new ArrayList<>();

    public Movie() {
    }

    private boolean suitability;
    private int releaseYear;
    private List<String> actors= new ArrayList<>();

    public Movie(int id, String title, String description, boolean suitability, int releaseYear, List<String> actors, int duration, String genre, List<String> relatedMovies, boolean suitability1, int releaseYear1, List<String> actors1) {
        super(id, title, description, suitability, releaseYear, actors);
        this.duration = duration;
        this.genre = genre;
        this.relatedMovies = relatedMovies;
        this.suitability = suitability1;
        this.releaseYear = releaseYear1;
        this.actors = actors1;
    }

    @Override
    public String toString() {
        return "Movie(title=" + getTitle() + ", description=" + getDescription() + ", suitability=" + isSuitability() + ", releaseYear=" + getReleaseYear() + ", actors=" + getActors() + ", duration=" + this.duration + ", genre=" + this.genre + ", relatedMovies=" + this.relatedMovies + ")";
    }
}
