package auth.cableTv.domain;

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
        return "Review(" +
                "reviewText=" + reviewText +
                ", rating=" + rating +
                ", title=" + title +
                ')';
    }

    public String getReviewText() {
        return reviewText;
    }

    public short getRating() {
        return rating;
    }

    public String getTitle() {
        return title;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public void setRating(short rating) {
        this.rating = rating;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
