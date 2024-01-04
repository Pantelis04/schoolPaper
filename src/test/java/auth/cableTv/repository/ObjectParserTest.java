package auth.cableTv.repository;

import auth.cableTv.domain.*;
import junit.framework.TestCase;

import java.util.Arrays;
import java.util.List;

public class ObjectParserTest extends TestCase {

    public void testParseMovieString() {

        ObjectParser movieParser = new ObjectParser();

        String movieString = "Movie(title=Test Movie, description=my movie, suitability=true, releaseYear=1992, actors=[Josh, Maria], duration=104, genre=Sci-Fi, relatedMovies=[A time in Hollywood, Batman])";
        Movie movie = movieParser.parseMovieString(movieString);

        // Check individual fields
        assertEqual("Test Movie", movie.getTitle());
        assertEqual("my movie", movie.getDescription());
        assertTrue(movie.isSuitability());
        assertEqual(1992, movie.getReleaseYear());
        assertEqual(104, movie.getDuration());
        assertEqual("Sci-Fi", movie.getGenre());  // Assuming genre is a single value in this example

        // Check actors
        String[] expectedActors = {"Josh", "Maria"};
        assertArrayEquals(expectedActors, movie.getActors().toArray());

        // Check related movies
        String[] expectedRelatedMovies = {"A time in Hollywood", "Batman"};
        assertArrayEquals(expectedRelatedMovies, movie.getRelatedMovies().toArray());

    }

    public static void testParseTvSeries() {
        String tvSeriesString = "TvSeries(title=The Office, description=Documentary, suitability=true, releaseYear=2006, actors=[Michael Scott, Jim Halpert], genre=Comedy, seasons=[Season(seasonNumber=1, releaseYear=2005, episodes=[Episode(episodeNumber=1, duration=25), Episode(episodeNumber=2, duration=22)]),Season(seasonNumber=2, releaseYear=2006, episodes=[Episode(episodeNumber=1, duration=28),Episode(episodeNumber=2, duration=24)])], relatedSeries=[Friends, How I met your mother])";
        ObjectParser tvSeriesParser = new ObjectParser();

        TvSeries tvSeries = tvSeriesParser.parseTvSeries(tvSeriesString);

        // Check individual fields
        assertEqual("The Office", tvSeries.getTitle());
        assertEqual("Documentary", tvSeries.getDescription());
        assertTrue(tvSeries.isSuitability());
        assertEqual(2006, tvSeries.getReleaseYear());
        assertEqual("Comedy", tvSeries.getGenre());

        // Check actors
        String[] expectedActors = {"Michael Scott", "Jim Halpert"};
        assertArrayEquals(expectedActors, tvSeries.getActors().toArray());

        // Check seasons
        List<Season> expectedSeasons = Arrays.asList(
                new Season(1, 2005, Arrays.asList(
                        new Episode(1, 25),
                        new Episode(2, 22)
                )),
                new Season(2, 2006, Arrays.asList(
                        new Episode(1, 28),
                        new Episode(2, 24)
                ))
        );
        assertEquals(expectedSeasons.size(), tvSeries.getSeasons().size());

        // Check related series
        String[] expectedRelatedSeries = {"Friends", "How I met your mother"};
        assertArrayEquals(expectedRelatedSeries, tvSeries.getRelatedSeries().toArray());
    }

    public static void testParseAdminString() {
        String adminString = "Admin(username=adminUser, password=adminPassword)";
        ObjectParser adminParser = new ObjectParser();

        Admin admin = adminParser.parseAdminString(adminString);

        // Check individual fields
        assertEqual("adminUser", admin.getUsername());
        assertEqual("adminPassword", admin.getPassword());
    }

    public static void testParseSubscriberString() {
        String subscriberString = "Subscriber(firstName=Maria, lastName=Maria, username=maria1, password=1234, favoriteMovies=[Inception, The Shawshank Redemption, The Matrix], favoriteTvSeries=[Breaking Bad, Game of Thrones, Naruto, The Office], reviews=[Review(reviewText=Great movie!, rating=5, title=Inception), Review(reviewText=nice movie, rating=3, title=The Matrix), Review(reviewText=great tv show, rating=5, title=The Office)])";
        ObjectParser subscriberParser = new ObjectParser();

        Subscriber subscriber = subscriberParser.parseSubscriberString(subscriberString);

        // Check individual fields
        assertEqual("Maria", subscriber.getFirstName());
        assertEqual("Maria", subscriber.getLastName());
        assertEqual("maria1", subscriber.getUsername());
        assertEqual("1234", subscriber.getPassword());  // Assuming password is stored as a String in your Subscriber class

        // Check favorite movies
        String[] expectedFavoriteMovies = {"Inception", "The Shawshank Redemption", "The Matrix"};
        assertArrayEquals(expectedFavoriteMovies, subscriber.getFavoriteMovies().toArray());

        // Check favorite TV series
        String[] expectedFavoriteTvSeries = {"Breaking Bad", "Game of Thrones", "Naruto", "The Office"};
        assertArrayEquals(expectedFavoriteTvSeries, subscriber.getFavoriteTvSeries().toArray());

    }

    private static void assertEqual(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", Actual: " + actual);
        }
    }

   public static void assertTrue(boolean condition) {
        if (!condition) {
            throw new AssertionError("Assertion failed: Expected true");
        }
    }

    private static void assertArrayEquals(Object[] expected, Object[] actual) {
        if (!Arrays.equals(expected, actual)) {
            throw new AssertionError("Array mismatch: Expected " + Arrays.toString(expected) + ", Actual " + Arrays.toString(actual));
        }
    }
}