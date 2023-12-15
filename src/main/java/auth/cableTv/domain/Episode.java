package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Episode {
    private int episodeNumber;
    private int duration;

    public Episode(int episodeNumber, int duration) {
        this.episodeNumber = episodeNumber;
        this.duration = duration;
    }
}
