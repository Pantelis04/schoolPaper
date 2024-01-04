package auth.cableTv.repository;

import auth.cableTv.domain.*;

import java.util.ArrayList;
import java.util.List;

public class ObjectParser {


    /**
     * Μέθοδος που μετατρέπει ενα String σε ένα αντικείμενο τύπου Movie.
     *
     * @param movieString Η συμβολοσειρά που περιέχει τα χαρακτηριστικά της ταινίας σε μορφή "Movie(title=ΤΙΤΛΟΣ, description=ΠΕΡΙΓΡΑΦΗ, suitability=ΚΑΤΑΛΛΗΛΟΤΗΤΑ,
     *                    releaseYear=ΧΡΟΝΟΛΟΓΙΑ, duration=ΔΙΑΡΚΕΙΑ, genre=ΕΙΔΟΣ)".
     * @return Το αντικείμενο τύπου Movie που περιλαμβάνει τις πληροφορίες της ταινίας.
     */
    public Movie parseMovieString(String movieString) {
        Movie movie = new Movie();

        // Extracting values from the string
        String[] parts = movieString.replaceAll("Movie\\(|\\)", "").split(", ");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) {
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
                    case "duration":
                        movie.setDuration(Integer.parseInt(value));
                        break;
                    case "genre":
                        movie.setGenre(value);
                        break;
                    default:
                        // Handle unknown key or ignore
                        break;
                }
            }
        }
        movie.setActors(extractActors(movieString));
        movie.setRelatedMovies(extractRelatedMovies(movieString));
        return movie;
    }


    /**
     * Μέθοδος που μετατρέπει ενα String σε λίστα απο Strings.
     *
     * @param input Η συμβολοσειρά που περιέχει τα χαρακτηριστικά της ταινίας, συμπεριλαμβανομένων των σχετικών ταινιών.
     * @return Λίστα που περιέχει τις σχετικές ταινίες που εξάγονται από τη συμβολοσειρά.
     */
    private List<String> extractRelatedMovies(String input) {
        List<String> movieList = new ArrayList<>();

        int actorsStartIndex = input.indexOf("relatedMovies=[");
        if (actorsStartIndex != -1) {
            String actorsSubstring = input.substring(actorsStartIndex + "relatedMovies=[".length());

            // Find the closing square bracket
            int actorsEndIndex = actorsSubstring.indexOf("]");
            if (actorsEndIndex != -1) {
                actorsSubstring = actorsSubstring.substring(0, actorsEndIndex);

                // Split the actors by comma and trim each actor
                String[] actorsArray = actorsSubstring.split(",");

                for (String actor : actorsArray) {

                    movieList.add(actor.trim());
                }
            }
        }

        return movieList;
    }


    /**
     * Μέθοδος που εξάγει τους ηθοποιούς από το String input.
     *
     * @param input Η συμβολοσειρά που περιέχει τα χαρακτηριστικά της ταινίας, συμπεριλαμβανομένων των ηθοποιών.
     * @return Λίστα που περιέχει τους ηθοποιούς που εξάγονται από τη συμβολοσειρά.
     */
    private List<String> extractActors(String input) {
        List<String> actorsList = new ArrayList<>();

        int actorsStartIndex = input.indexOf("actors=[");
        if (actorsStartIndex != -1) {
            String actorsSubstring = input.substring(actorsStartIndex + "actors=[".length());

            // Find the closing square bracket
            int actorsEndIndex = actorsSubstring.indexOf("]");
            if (actorsEndIndex != -1) {
                actorsSubstring = actorsSubstring.substring(0, actorsEndIndex);

                // Split the actors by comma and trim each actor
                String[] actorsArray = actorsSubstring.split(",");

                for (String actor : actorsArray) {

                    actorsList.add(actor.trim());
                }
            }
        }

        return actorsList;
    }


    /**
     * Μέθοδος που μετατρέπει ενα String σε ένα αντικείμενο τύπου TvSeries.
     *
     * @param tvSeriesString Η συμβολοσειρά που περιέχει τα χαρακτηριστικά της ταινίας σε μορφή "TvSeries(title=ΤΙΤΛΟΣ, description=ΠΕΡΙΓΡΑΦΗ, suitability=ΚΑΤΑΛΛΗΛΟΤΗΤΑ,
     *                    releaseYear=ΧΡΟΝΟΛΟΓΙΑ, genre=ΕΙΔΟΣ, ...)".
     * @return Το αντικείμενο τύπου TvSeries που περιλαμβάνει τις πληροφορίες της σειράς.
     */
    public TvSeries parseTvSeries(String tvSeriesString) {
        TvSeries tvSeries = new TvSeries();

        // Extracting values from the string
        String[] parts = tvSeriesString.replaceAll("TvSeries\\(|\\)", "").split(", ");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                switch (key) {
                    case "title":
                        tvSeries.setTitle(value);
                        break;
                    case "description":
                        tvSeries.setDescription(value);
                        break;
                    case "suitability":
                        tvSeries.setSuitability(Boolean.parseBoolean(value));
                        break;
                    case "releaseYear":
                        tvSeries.setReleaseYear(Integer.parseInt(value));
                        break;
                    case "genre":
                        tvSeries.setGenre(value);
                        break;
                    default:
                        // Handle unknown key or ignore
                        break;
                }
                tvSeries.setActors(extractActors(tvSeriesString));
                tvSeries.setRelatedSeries(extractRelatedTvSeries(tvSeriesString));
                List<Season> seasons = parseSeasonList(tvSeriesString);
                tvSeries.setSeasons(seasons);
            }
        }

        return tvSeries;
    }

    private List<String> extractRelatedTvSeries(String input) {
        List<String> movieList = new ArrayList<>();

        int actorsStartIndex = input.indexOf("relatedSeries=[");
        if (actorsStartIndex != -1) {
            String actorsSubstring = input.substring(actorsStartIndex + "relatedSeries=[".length());

            // Find the closing square bracket
            int actorsEndIndex = actorsSubstring.indexOf("]");
            if (actorsEndIndex != -1) {
                actorsSubstring = actorsSubstring.substring(0, actorsEndIndex);

                // Split the actors by comma and trim each actor
                String[] actorsArray = actorsSubstring.split(",");

                for (String actor : actorsArray) {

                    movieList.add(actor.trim());
                }
            }
        }

        return movieList;
    }

    public List<Season> parseSeasonList(String input) {
        Season season = new Season();

        List<Season> seasons = new ArrayList<>();
        int index = 0;

        while (index < input.length()) {
            // Find the start of an Episode
            int seasonStartIndex = input.indexOf("Season(", index);
            if (seasonStartIndex == -1) {
                break;
            }
            int seasonEndIndex = input.indexOf(")", seasonStartIndex);
            if (seasonEndIndex == -1) {
                break;
            }

            // Extract the Episode substring
            String seasonString = input.substring(seasonStartIndex, seasonEndIndex + 1);
            seasons.add(parseSeason(seasonString));

            // Move the index to the next position
            index = seasonEndIndex + 1;
        }
        return seasons;
    }

    private Season parseSeason(String seasonString) {
        String[] parts = seasonString.split(",");

        // Extract episode number from the first part
        int seasonNumber = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1).trim());

        // Extract duration from the second part

        int releaseYear = Integer.parseInt(parts[1].replace(")", "").substring(parts[1].indexOf('=') + 1).trim());

        // Create and return an Episode object
        List<Episode> episodes = parseEpisodeList(seasonString);
        return new Season(seasonNumber, releaseYear, episodes);

    }

    public List<Episode> parseEpisodeList(String input) {
        List<Episode> episodes = new ArrayList<>();
        int index = 0;

        while (index < input.length()) {
            // Find the start of an Episode
            int episodeStartIndex = input.indexOf("Episode(", index);
            if (episodeStartIndex == -1) {
                break;
            }
            int episodeEndIndex = input.indexOf(")", episodeStartIndex);
            if (episodeEndIndex == -1) {
                break;
            }

            // Extract the Episode substring
            String episodeString = input.substring(episodeStartIndex, episodeEndIndex + 1);
            episodes.add(parseEpisode(episodeString));

            // Move the index to the next position
            index = episodeEndIndex + 1;
        }

        return episodes;

    }

    private Episode parseEpisode(String episodeString) {
        String[] parts = episodeString.split(",");

        // Extract episode number from the first part
        int episodeNumber = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1).trim());

        // Extract duration from the second part

        int duration = Integer.parseInt(parts[1].replace(")", "").substring(parts[1].indexOf('=') + 1).trim());

        // Create and return an Episode object
        return new Episode(episodeNumber, duration);
    }



    /**
     * Μέθοδος που μετατρέπει ενα String σε ένα αντικείμενο τύπου Admin.
     *
     * @param adminString
     * @return Το αντικείμενο τύπου Admin.
     */
    public Admin parseAdminString(String adminString) {
        Admin admin = new Admin();
        // Extracting values from the string
        String[] parts = adminString.replaceAll("Admin\\(|\\)", "").split(", ");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) {
                    case "username":
                        admin.setUsername(value);
                        break;
                    case "password":
                        admin.setPassword(value);
                        break;
                    default:
                        // Handle unknown key or ignore
                        break;
                }
            }
        }

        return admin;
    }

    /**
     * Μέθοδος που μετατρέπει ενα String σε ένα αντικείμενο τύπου Subscriber.
     *
     * @param subscriberString
     * @return Το αντικείμενο τύπου Subscriber.
     */
    public Subscriber parseSubscriberString(String subscriberString) {
        Subscriber subscriber = new Subscriber();

        // Extracting values from the string
        String[] parts = subscriberString.replaceAll("Subscriber\\(|\\)", "").split(", ");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();

                switch (key) {
                    case "username":
                        subscriber.setUsername(value);
                        break;
                    case "password":
                        subscriber.setPassword(value);
                        break;
                    case "firstName":
                        subscriber.setFirstName(value);
                        break;
                    case "lastName":
                        subscriber.setLastName(value);
                        break;
                    default:
                        // Handle unknown key or ignore
                        break;
                }
            }

        }
        subscriber.setFavoriteMovies(extractFavoriteMovies(subscriberString));
        subscriber.setFavoriteTvSeries(extractFavoriteTvSeries(subscriberString));
        subscriber.setReviews(parseReviewsList(subscriberString));
        return subscriber;
    }

    public List<Review> parseReviewsList(String input) {
        Review season = new Review();

        List<Review> reviews = new ArrayList<>();
        int index = 0;

        while (index < input.length()) {
            // Find the start of an Episode
            int reviewStartIndex = input.indexOf("Review(", index);
            if (reviewStartIndex == -1) {
                break;
            }
            int reviewEndIndex = input.indexOf(")", reviewStartIndex);
            if (reviewEndIndex == -1) {
                break;
            }

            // Extract the Episode substring
            String reviewString = input.substring(reviewStartIndex, reviewEndIndex + 1);
            reviews.add(parseReview(reviewString));

            // Move the index to the next position
            index = reviewEndIndex + 1;
        }
        return reviews;
    }

    private Review parseReview(String reviewString) {
        String[] parts = reviewString.split(",");
        String reviewText = parts[0].substring(parts[0].indexOf('=') + 1).trim();
        short rating = Short.parseShort(parts[1].replace(")", "").substring(parts[1].indexOf('=') + 1).trim());
        String title = parts[2].substring(parts[2].indexOf('=') + 1).trim().replace(")", "");
        return new Review(reviewText, rating, title);

    }

    private List<String> extractFavoriteMovies(String input) {
        List<String> list = new ArrayList<>();

        int actorsStartIndex = input.indexOf("favoriteMovies=[");
        if (actorsStartIndex != -1) {
            String substring = input.substring(actorsStartIndex + "favoriteMovies=[".length());

            // Find the closing square bracket
            int actorsEndIndex = substring.indexOf("]");
            if (actorsEndIndex != -1) {
                substring = substring.substring(0, actorsEndIndex);

                // Split the actors by comma and trim each actor
                String[] array = substring.split(",");

                for (String ar : array) {

                    list.add(ar.trim());
                }
            }
        }

        return list;
    }

    private List<String> extractFavoriteTvSeries(String input) {
        List<String> list = new ArrayList<>();

        int actorsStartIndex = input.indexOf("favoriteTvSeries=[");
        if (actorsStartIndex != -1) {
            String substring = input.substring(actorsStartIndex + "favoriteTvSeries=[".length());

            // Find the closing square bracket
            int actorsEndIndex = substring.indexOf("]");
            if (actorsEndIndex != -1) {
                substring = substring.substring(0, actorsEndIndex);

                // Split the actors by comma and trim each actor
                String[] array = substring.split(",");

                for (String ar : array) {

                    list.add(ar.trim());
                }
            }
        }

        return list;
    }

}
