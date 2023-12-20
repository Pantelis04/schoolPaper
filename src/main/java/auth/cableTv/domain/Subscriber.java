package auth.cableTv.domain;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Subscriber {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<String> favoriteMovies;
    private List<String> favoriteTvSeries;
    private List<Review> reviews;


    public Subscriber() {
    }

    public Subscriber(String firstName, String lastName, String username, String password, List<String> favoriteMovies, List<String> favoriteTvSeries, List<Review> reviews) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.favoriteMovies = favoriteMovies;
        this.favoriteTvSeries = favoriteTvSeries;
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "Subscriber(firstName=" + getFirstName() + ", lastName=" + getLastName() + ", username=" + getUsername() + ", password=" + getPassword() + ", favoriteMovies=" + getFavoriteMovies() + ", favoriteTvSeries=" + getFavoriteTvSeries() + ", reviews=" + getReviews().toString() + ")";
    }
}
