package auth.cableTv;

import auth.cableTv.repository.MovieRepositoryImpl;

/**
 * Hello world!
 */
public class App {
    private static final String JDBC_URL = "jdbc:sqlite:cableTv.db";

    public static void main(String[] args) {
        createTables();
    }

    private static void createTables() {

        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();


        System.out.println(movieRepository.getMoviesByGenre("Sci-Fi"));

    }
}






