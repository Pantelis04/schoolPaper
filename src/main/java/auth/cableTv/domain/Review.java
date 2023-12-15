package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private String reviewText;
    private short rating;

    public Review(String reviewText, short rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
