package auth.cableTv.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
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
}
