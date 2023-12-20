package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Review {
    private String reviewText;
    private short rating;

    private String title;


    public Review() {
    }

    public Review(String reviewText, short rating, String title) {
        this.reviewText = reviewText;
        this.rating = rating;
        this.title = title;
    }

    @Override
    public String toString() {
        return "Review{" +
                "reviewText='" + reviewText + '\'' +
                ", rating=" + rating +
                ", title='" + title + '\'' +
                '}';
    }

}
