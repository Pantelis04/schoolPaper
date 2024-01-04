package auth.cableTv.domain;

import java.util.ArrayList;
import java.util.List;


public class Subscriber {
    private String firstName;
    private String lastName;
    private String username;
    private String password;
    private List<String> favoriteMovies= new ArrayList<>();
    private List<String> favoriteTvSeries= new ArrayList<>();
    private List<Review> reviews= new ArrayList<>();

    public Subscriber(String firstName, String lastName, String username, String password) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.username = username;
        this.password = password;
    }


    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setFavoriteMovies(List<String> favoriteMovies) {
        this.favoriteMovies = favoriteMovies;
    }

    public void setFavoriteTvSeries(List<String> favoriteTvSeries) {
        this.favoriteTvSeries = favoriteTvSeries;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

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

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public List<String> getFavoriteMovies() {
        return favoriteMovies;
    }

    public List<String> getFavoriteTvSeries() {
        return favoriteTvSeries;
    }

    public List<Review> getReviews() {
        return reviews;
    }
}
