package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

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

    public Movie() {
    }

    private boolean suitability;
    private int releaseYear;
    private List<String> actors;

    @Override
    public String toString() {
        return "Movie(id=" + super.getId() + ", title=" + super.getTitle() + ", description=" + super.getDescription() + ", suitability=" + super.isSuitability() + ", releaseYear=" + super.getReleaseYear() + ", actors=" + super.getActors() + ", duration=" + this.duration + ", genre=" + this.genre + ", relatedMovies=" + this.relatedMovies + ")";


    }
}
