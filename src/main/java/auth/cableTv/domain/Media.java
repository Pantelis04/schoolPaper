package auth.cableTv.domain;

import java.util.ArrayList;
import java.util.List;


public class Media {
    private String title;

    private String genre;
    private String description;
    private boolean suitability;
    private int releaseYear;
    private List<String> actors = new ArrayList<>();


    public Media() {
    }

    public Media(String title, String genre, String description, boolean suitability, int releaseYear, List<String> actors) {
        this.title = title;
        this.genre = genre;
        this.description = description;
        this.suitability = suitability;
        this.releaseYear = releaseYear;
        this.actors = actors;
    }


    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public boolean isSuitability() {
        return suitability;
    }

    public int getReleaseYear() {
        return releaseYear;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setSuitability(boolean suitability) {
        this.suitability = suitability;
    }

    public void setReleaseYear(int releaseYear) {
        this.releaseYear = releaseYear;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }
}
