package auth.cableTv;

import auth.cableTv.gui.Gui;

import javax.swing.*;

/**
 * Hello world!
 */
public class App {
    private static final String JDBC_URL = "jdbc:sqlite:cableTv.db";

    public static void main(String[] args) {
        createTables();
    }

    private static void createTables() {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new Gui().setVisible(true);
            }
        });
//        Repository movieRepository = new Repository();
//        Review review = new Review();
//        review.setReviewText("Great movie!");
//        review.setRating((short) 5);
//        review.setTitle("Inception");
//
//        // Create a new Subscriber object
//        Subscriber subscriber = new Subscriber();
//        subscriber.setFirstName("John");
//        subscriber.setLastName("Doe");
//        subscriber.setUsername("john.doe");
//        subscriber.setPassword("securePassword");
//        subscriber.setFavoriteMovies(List.of("Inception", "The Shawshank Redemption"));
//        subscriber.setFavoriteTvSeries(List.of("Breaking Bad", "Game of Thrones"));
//        subscriber.setReviews(List.of(review));
//        movieRepository.saveLine("subscriber.txt",subscriber.toString());
//        ObjectParser objectParser = new ObjectParser();
//        System.out.println(ObjectParser.parseSubscriberString(movieRepository.getLines("subscriber.txt",List.of("john.doe")).get(0)));
//        Repository repository = new Repository();
//        List<String> lines = repository.getLines("admin.txt", List.of("trunks","12563"));


////
//
//        ObjectParser objectParser = new ObjectParser();
//        Repository movieRepository = new Repository();
//        movieRepository.saveLine("admin.txt",new Admin("trunks","12563").toString());
////
//        Movie updatedMovie = new Movie();
//        updatedMovie.setId(5);
//        updatedMovie.setTitle("Pulp Fiction");
//        updatedMovie.setDescription("Quirky crime");
//        updatedMovie.setSuitability(true);
//        updatedMovie.setReleaseYear(1994);
//        updatedMovie.setActors(List.of("John Travolta", "Uma Thurman"));
//        updatedMovie.setDuration(154);
//        updatedMovie.setGenre("Comedy");
//

//        Movie movie = new Movie();
//        movie.setTitle("Pulp Fiction");
//        movie.setDescription("Quirky crime");
//        movie.setSuitability(true);
//        movie.setReleaseYear(1994);
//        movie.setActors(List.of("John Travolta", "Uma Thurman"));
//        movie.setDuration(154);
//        movie.setGenre("Crime");
//        movie.setRelatedMovies(List.of("Et","Rambo"));
//
////        movieRepository.deleteLineByString("movies.txt",movie.toString());
////        movieRepository.updateLineByString("movies.txt",movie.toString(),updatedMovie.toString());
////        System.out.println(movieRepository.getLines("movies.txt",List.of("Jack Nicholson")));
//
////        System.out.println(ObjectParser.parseMovieString(movieRepository.getLines("movies.txt",List.of("Pulp Fiction")).get(0)));
//            movieRepository.saveLine("movies.txt",movie.toString());
////        System.out.println(tvSeries);
//
//        System.out.println(ObjectParser.parseTvSeries(movieRepository.getLines("tvseries.txt",List.of("The Office")).get(0)));
//
//
//        Episode episode1Season1 = new Episode(1, 25);
//        Episode episode2Season1 = new Episode(2, 22);
//        Season season1 = new Season(1, 2005, List.of(episode1Season1, episode2Season1));
//
//        // Create Season 2
//        Episode episode1Season2 = new Episode(1, 28);
//        Episode episode2Season2 = new Episode(2, 24);
//        Season season2 = new Season(2, 2006, List.of(episode1Season2, episode2Season2));
//
//        // Create TvSeries
//        TvSeries tvSeries = new TvSeries(10, "The Office14", "Mockumentary", true, 2005,
//                List.of("Michael Scott", "Jim Halpert"), "Comedy", List.of(season1, season2),
//                List.of("Friends", "How I met your mother"));

//        movieRepository.saveLine("tvseries.txt",tvSeries.toString());
//       System.out.println(ObjectParser.parseTvSeries(movieRepository.getLines("tvseries.txt",List.of("The Office14")).get(0)));
//        System.out.println(ObjectParser.parseMovieString(movieRepository.getLines("movies.txt",List.of("Pulp Fiction")).get(0)));
    }
}






