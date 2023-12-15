package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Season {
    private int seasonNumber;
    private int releaseYear;
    private List<Episode> episodes;

    public Season(int seasonNumber, int releaseYear, List<Episode> episodes) {
        this.seasonNumber = seasonNumber;
        this.releaseYear = releaseYear;
        this.episodes = episodes;
    }
}
