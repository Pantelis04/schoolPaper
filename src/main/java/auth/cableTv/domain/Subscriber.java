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
    private List<Movie> favoriteMovies;
    private List<TvSeries> favoriteTvSeries;
    private List<Review> reviews;


    public Subscriber(String firstName, String lastName, String username, String password, List<Movie> favoriteMovies, List<TvSeries> favoriteTvSeries, List<Review> reviews) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
        this.favoriteMovies = favoriteMovies;
        this.favoriteTvSeries = favoriteTvSeries;
        this.reviews = reviews;
    }
}
