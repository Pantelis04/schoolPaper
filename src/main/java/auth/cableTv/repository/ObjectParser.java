package auth.cableTv.repository;

import auth.cableTv.domain.Episode;
import auth.cableTv.domain.Movie;
import auth.cableTv.domain.Season;
import auth.cableTv.domain.TvSeries;

import java.util.ArrayList;
import java.util.List;

public class ObjectParser {

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

    private static List<String> extractRelatedMovies(String input) {
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


    private static List<String> extractActors(String input) {
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


    public static TvSeries parseTvSeries(String tvSeriesString) {
        TvSeries tvSeries = new TvSeries();

        // Extracting values from the string
        String[] parts = tvSeriesString.replaceAll("TvSeries\\(|\\)", "").split(", ");
        for (String part : parts) {
            String[] keyValue = part.split("=");
            if (keyValue.length == 2) {
                String key = keyValue[0].trim();
                String value = keyValue[1].trim();
                switch (key) {
                    case "id":
                        tvSeries.setId(Integer.parseInt(value));
                        break;
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
                    case "category":
                        tvSeries.setCategory(value);
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

    private static List<String> extractRelatedTvSeries(String input) {
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

    public static List<Season>parseSeasonList(String input) {
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

    private static Season parseSeason(String seasonString) {
        String[] parts = seasonString.split(",");

        // Extract episode number from the first part
        int seasonNumber = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1).trim());

        // Extract duration from the second part

        int releaseYear = Integer.parseInt(parts[1].replace(")","").substring(parts[1].indexOf('=') + 1).trim());

        // Create and return an Episode object
        List<Episode> episodes = parseEpisodeList(seasonString);
        return new Season(seasonNumber, releaseYear,episodes);

    }

    public static List<Episode> parseEpisodeList(String input) {
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

    private static Episode parseEpisode(String episodeString) {
        String[] parts = episodeString.split(",");

        // Extract episode number from the first part
        int episodeNumber = Integer.parseInt(parts[0].substring(parts[0].indexOf('=') + 1).trim());

        // Extract duration from the second part

        int duration = Integer.parseInt(parts[1].replace(")","").substring(parts[1].indexOf('=') + 1).trim());

        // Create and return an Episode object
        return new Episode(episodeNumber, duration);
    }


    private static List<String> parseStringList(String value) {
        // Assuming the input string is a comma-separated list
        String stripValue = value.replace("[", "").replace("]", "");

        String[] elements = stripValue.split(", ");
        return List.of(elements);
    }


}
