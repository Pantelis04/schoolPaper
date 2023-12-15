package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TvSeries extends Media {
    private String category;
    private List<String> actors;
    private List<Season> seasons;
    private List<String> relatedSeries;


    public TvSeries(String category, List<String> actors, List<Season> seasons, List<String> relatedSeries) {
        this.category = category;
        this.actors = actors;
        this.seasons = seasons;
        this.relatedSeries = relatedSeries;
    }

    public TvSeries(int id, String title, String description, boolean suitability, int releaseYear, String category, List<String> actors, List<Season> seasons, List<String> relatedSeries) {
        super(id, title, description, suitability, releaseYear);
        this.category = category;
        this.actors = actors;
        this.seasons = seasons;
        this.relatedSeries = relatedSeries;
    }
}
