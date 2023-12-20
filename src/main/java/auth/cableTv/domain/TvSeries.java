package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TvSeries extends Media {
    private String category;
    private List<Season> seasons = new ArrayList<>();
    private List<String> relatedSeries;


    public TvSeries(int id, String title, String description, boolean suitability, int releaseYear, List<String> actors, String category, List<Season> seasons, List<String> relatedSeries) {
        super(id, title, description, suitability, releaseYear, actors);
        this.category = category;
        this.seasons = seasons;
        this.relatedSeries = relatedSeries;

    }

    public TvSeries() {
    }

    @Override
    public String toString() {
        return "TvSeries(title=" +getTitle() +
                ", description=" +getDescription() +
                ", suitability=" +isSuitability() +
                ", releaseYear=" +getReleaseYear() +
                ", actors=" +getActors() +
                ", category=" + getCategory() +
                ", seasons=" + getSeasons().toString() +
                ", relatedSeries=" + getRelatedSeries() +
                ")";
    }

}
