package auth.cableTv.domain;

import java.util.List;


public class Media {
    private int id;
    private String title;
    private String description;
    private boolean suitability;
    private int releaseYear;
    private List<String> actors;


    public Media(int id, String title, String description, boolean suitability, int releaseYear, List<String> actors) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.suitability = suitability;
        this.releaseYear = releaseYear;
        this.actors = actors;
    }

    public Media() {
    }

    public int getId() {
        return id;
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

    public void setId(int id) {
        this.id = id;
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
}
