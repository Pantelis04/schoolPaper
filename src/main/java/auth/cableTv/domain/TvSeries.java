package auth.cableTv.domain;

import java.util.ArrayList;
import java.util.List;


public class TvSeries extends Media {

    private List<Season> seasons = new ArrayList<>();
    private List<String> relatedSeries;


    public TvSeries() {
    }

    public TvSeries(String title, String genre, String description, boolean suitability, int releaseYear, List<String> actors, List<Season> seasons, List<String> relatedSeries) {
        super(title, genre, description, suitability, releaseYear, actors);
        this.seasons = seasons;
        this.relatedSeries = relatedSeries;
    }

    @Override
    public String toString() {
        return "TvSeries(title=" + getTitle() +
                ", description=" + getDescription() +
                ", suitability=" + isSuitability() +
                ", releaseYear=" + getReleaseYear() +
                ", actors=" + getActors() +
                ", genre=" + getGenre() +
                ", seasons=" + getSeasons().toString() +
                ", relatedSeries=" + getRelatedSeries() +
                ")";
    }

    public List<Season> getSeasons() {
        return seasons;
    }

    public List<String> getRelatedSeries() {
        return relatedSeries;
    }

    public void setSeasons(List<Season> seasons) {
        this.seasons = seasons;
    }

    public void setRelatedSeries(List<String> relatedSeries) {
        this.relatedSeries = relatedSeries;
    }
}
