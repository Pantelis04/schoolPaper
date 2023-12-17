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

//            Movie movie = new Movie();
//            movie.setId(1);
//            movie.setTitle("Rambo");
//            movie.setDescription("Soldiers fighting");
//            movie.setReleaseYear(1990);
//            movie.setSuitability(true);
//            movie.setActors(List.of("Stalone","Britney"));
        MovieRepositoryImpl movieRepository = new MovieRepositoryImpl();
//            movieRepository.saveMovie(movie);

        System.out.println(movieRepository.getMoviesByGenre("Sci-Fi"));

    }
}






