package auth.cableTv.repository;

import auth.cableTv.domain.Movie;

import java.util.ArrayList;
import java.util.List;


public class RepositoryMovies {

    Repository repository = new Repository();
    ObjectParser objectParser = new ObjectParser();
    String moviesFile = "movies.txt";

    public List<Movie> findMovies(String title, String description, String genre, String duration, String suitability, List<String> actors, String releaseYear, List<String> relatedMovies) {
        List<String> stringList = new ArrayList<>();
        stringList.add("title=" + title);
        stringList.add("genre=" + genre);
        stringList.add("description=" + description);
        stringList.add("releaseYear=" + releaseYear);
        stringList.add("suitability=" + suitability);
        stringList.add("duration=" + duration);
        for (String actor : actors) {
            stringList.add(actor);
        }
        for (String movieTitle : relatedMovies) {
            stringList.add(title);
        }

        List<String> moviesString = repository.getLines(moviesFile, stringList);
        List<Movie> movies = new ArrayList<>();
        for (String movieString : moviesString) {
            Movie movieFound = objectParser.parseMovieString(movieString);
            if (movieFound.getTitle().contains(title) || listAContainsListB(movieFound.getRelatedMovies(), relatedMovies) || movieFound.getDescription().contains(description) || listAContainsListB(movieFound.getActors(), actors)) {
                if (duration != null && !duration.isEmpty()) {
                    if (movieFound.getDuration() == Integer.parseInt(duration)) {
                        movies.add(movieFound);
                    }
                } else {
                    movies.add(movieFound);
                }
            }
        }
        return movies;
    }


    public void addAMovie(Movie movie) {
        repository.saveLine(moviesFile, movie.toString());
    }

    private boolean listAContainsListB(List<String> listA, List<String> listB) {
        for (String value : listB) {
            if (!listA.contains(value)) {
                return false;
            }
        }
        return true;
    }

    public int deleteMovie(Movie movie) {
        repository.deleteLineByString(moviesFile, movie.getTitle());
        return 1;
    }

    public int updateMovie(String movieTitle, Movie newMovie) {
        repository.updateLineByString(moviesFile, movieTitle, newMovie.toString());
        return 1;
    }

    public int saveMovie(Movie movie) {
        repository.saveLine(moviesFile, movie.toString());
        return 1;
    }


}
