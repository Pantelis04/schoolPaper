package auth.cableTv.repository;

import auth.cableTv.domain.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class MovieRepositoryImpl {

    private static final String MOVIE_FILE_PATH = "movies.txt";

    public void saveMovie(Movie movie) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(MOVIE_FILE_PATH, true))) {
            writer.write(movie.toString());
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Movie getMovieById(int id) {
        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Movie movie = parseMovieString(line);
                if (movie != null && movie.getId() == id) {
                    return movie;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Movie not found
    }

    public Movie getMovieByTitle(String title) {
        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Movie movie = parseMovieString(line);
                if (movie != null && movie.getTitle().equals(title)) {
                    return movie;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null; // Movie not found
    }

    public List<Movie> getMoviesByGenre(String genre) {
        List<Movie> movies = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(MOVIE_FILE_PATH))) {
            String line;
            while ((line = reader.readLine()) != null) {
               //skip empty lines
                if (line.trim().isEmpty()) {
                    continue;
                }

                Movie movie = parseMovieString(line);
                if (movie != null && movie.getGenre().equals(genre)) {
                    movies.add(movie);
                }
            }
            return movies;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Movie parseMovieString(String movieString) {
        Movie movie = new Movie();

        // Extracting values from the string
        String[] parts = movieString.replaceAll("Movie\\(|\\)", "").split(", ");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) {
                    case "id":
                        movie.setId(Integer.parseInt(value));
                        break;
                    case "title":
                        movie.setTitle(value);
                        break;
                    case "description":
                        movie.setDescription(value);
                        break;
                    case "suitability":
                        movie.setSuitability(Boolean.parseBoolean(value));
                        break;
                    case "releaseYear":
                        movie.setReleaseYear(Integer.parseInt(value));
                        break;
                    case "actors":
                        // Handle actors parsing if needed
                        break;
                    case "duration":
                        movie.setDuration(Integer.parseInt(value));
                        break;
                    case "genre":
                        movie.setGenre(value);
                        break;
                    case "relatedMovies":
                        // Handle relatedMovies parsing if needed
                        break;
                    default:
                        // Handle unknown key or ignore
                        break;
                }
            }
        }

        return movie;
    }

}
