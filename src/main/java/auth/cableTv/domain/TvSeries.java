package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class TvSeries extends Media {
    private String category;
    private List<Season> seasons;
    private List<String> relatedSeries;


    public TvSeries(int id, String title, String description, boolean suitability, int releaseYear, List<String> actors, String category, List<Season> seasons, List<String> relatedSeries) {
        super(id, title, description, suitability, releaseYear, actors);
        this.category = category;
        this.seasons = seasons;
        this.relatedSeries = relatedSeries;

    }
}
