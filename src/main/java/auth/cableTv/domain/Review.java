package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private String reviewText;
    private int rating;

    public Review(String reviewText, int rating) {
        this.reviewText = reviewText;
        this.rating = rating;
    }
}
