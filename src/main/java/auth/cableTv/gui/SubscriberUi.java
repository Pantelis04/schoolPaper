package auth.cableTv.gui;

import auth.cableTv.domain.Movie;
import auth.cableTv.domain.Review;
import auth.cableTv.domain.Subscriber;
import auth.cableTv.domain.TvSeries;
import auth.cableTv.repository.ObjectParser;
import auth.cableTv.repository.RepositoryMovies;
import auth.cableTv.repository.RepositorySubscriber;
import auth.cableTv.repository.RepositoryTvSeries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Arrays;
import java.util.List;

public class SubscriberUi {

    private RepositoryMovies repositoryMovies = new RepositoryMovies();
    private RepositoryTvSeries repositoryTvSeries = new RepositoryTvSeries();
    private RepositorySubscriber repositorySubscriber = new RepositorySubscriber();

    private ObjectParser objectParser = new ObjectParser();


    void showSubscriberPanel(String username) {

        JFrame adminFrame = new JFrame("Admin Panel - " + username);
        adminFrame.setSize(1600, 600);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLayout(new FlowLayout());
        adminFrame.add(searchPanelMovies());
        adminFrame.add(searchPanelTvSeries());
        adminFrame.add(subscriberPanel(username));
        adminFrame.setVisible(true);
        adminFrame.setLocationRelativeTo(null);
    }

    JPanel searchPanelMovies() {

        JPanel moviesSearchPanel = new JPanel();
        moviesSearchPanel.setBackground(Color.LIGHT_GRAY);
        moviesSearchPanel.setSize(1500, 200);

        JTextField titleField = new JTextField(6);


        JTextField genreField = new JTextField(6);

        JTextField descriptionField = new JTextField(6);

        JTextField durationField = new JTextField(6);

        JTextField suitabilityField = new JTextField(6);

        JTextField actorsField = new JTextField(6);

        JTextField releaseYearField = new JTextField(6);

        JTextField relatedMoviesField = new JTextField(6);


        JButton searchButton = new JButton("Search");
        JTextArea searchResultArea = new JTextArea(10, 40);

        JScrollPane searchScrollPane = new JScrollPane(searchResultArea);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String genre = genreField.getText();
                String description = descriptionField.getText();
                String duration = durationField.getText();
                String suitability = suitabilityField.getText(); // Assuming suitability is a boolean
                String actors = actorsField.getText();
                String releaseYear = releaseYearField.getText();
                String relatedMovies = relatedMoviesField.getText();

                String[] moviesArray = relatedMovies.split(",");
                java.util.List<String> relatedMoviesList = Arrays.asList(moviesArray);
                String[] actorsArray = actors.split(",");
                java.util.List<String> actorsList = Arrays.asList(actorsArray);

                java.util.List<Movie> searchResults = repositoryMovies.findMovies(title, description, genre, duration, suitability, actorsList, releaseYear, relatedMoviesList);
                String movies = "";
                for (Movie movie : searchResults) {
                    String displayMovie = "Title: " + movie.getTitle() + ", Genrne: " + movie.getGenre() + ", Description: " + movie.getDescription() +
                            ", Duration: " + movie.getDuration() + ", Release: " + movie.getReleaseYear() + ", Actors: " + movie.getActors() + ", Related movies: " + movie.getRelatedMovies();
                    movies = displayMovie + "\n" + movies;
                }

                if (!movies.isEmpty()) {

                    searchResultArea.setText(movies);
                    searchResultArea.repaint();


                } else {
                    searchResultArea.setText("No movie found");
                    searchResultArea.repaint();
                }
            }
        });


        moviesSearchPanel.add(new JLabel("Movies"));
        moviesSearchPanel.add(new JLabel("__________"));
        moviesSearchPanel.add(new JLabel("Title:"));
        moviesSearchPanel.add(titleField);

        moviesSearchPanel.add(new JLabel("Genre:"));
        moviesSearchPanel.add(genreField);

        moviesSearchPanel.add(new JLabel("Description:"));
        moviesSearchPanel.add(descriptionField);

        moviesSearchPanel.add(new JLabel("Duration:"));
        moviesSearchPanel.add(durationField);

        moviesSearchPanel.add(new JLabel("Suitability:"));
        moviesSearchPanel.add(suitabilityField);

        moviesSearchPanel.add(new JLabel("Actors:"));
        moviesSearchPanel.add(actorsField);

        moviesSearchPanel.add(new JLabel("Release Year:"));
        moviesSearchPanel.add(releaseYearField);

        moviesSearchPanel.add(new JLabel("Related Movies:"));
        moviesSearchPanel.add(relatedMoviesField);

        moviesSearchPanel.add(searchButton);

        moviesSearchPanel.add(searchScrollPane);


        moviesSearchPanel.setLayout(new BoxLayout(moviesSearchPanel, BoxLayout.Y_AXIS));
        return moviesSearchPanel;

    }

    JPanel searchPanelTvSeries() {

        JPanel tvSeriesSearchPanel = new JPanel();
        tvSeriesSearchPanel.setBackground(Color.LIGHT_GRAY);

        JTextField titleField = new JTextField(5);

        JTextField genreField = new JTextField(5);

        JTextField descriptionField = new JTextField(5);

        JTextField suitabilityField = new JTextField(5);

        JTextField actorsField = new JTextField(5);

        JTextField releaseYearField = new JTextField(5);

        JTextField relatedTvSeriesField = new JTextField(5);

        JTextField seasonsFiled = new JTextField(5);


        JButton searchButton = new JButton("Search");
        JTextArea searchResultArea = new JTextArea(10, 40);
        JPanel newPanel = new JPanel();
        JScrollPane searchScrollPane = new JScrollPane(searchResultArea);
        newPanel.add(searchScrollPane);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String genre = genreField.getText();
                String description = descriptionField.getText();
                String suitability = suitabilityField.getText(); // Assuming suitability is a boolean
                String actors = actorsField.getText();
                String releaseYear = releaseYearField.getText();
                String relatedTvSeries = relatedTvSeriesField.getText();

                String[] seriesArray = relatedTvSeries.split(", ");
                java.util.List<String> relatedTvSeriesList = Arrays.asList(seriesArray);

                String[] actorsArray = actors.split(", ");
                java.util.List<String> actorsList = Arrays.asList(actorsArray);

                java.util.List<TvSeries> searchResults = repositoryTvSeries.findTvSeries(title, description, genre, suitability, actorsList, releaseYear, relatedTvSeriesList);

                String tvSeries = "";
                for (TvSeries series : searchResults) {
                    String displaySeries = "Title: " + series.getTitle() + ", Genrne: " + series.getGenre() + ", Description: " + series.getDescription() + ", Release: " + series.getReleaseYear() + ", Actors: " + series.getActors() + ", Related series: " + series.getRelatedSeries() +
                            ", Seasons: " + series.getSeasons();
                    tvSeries = displaySeries + "\n" + tvSeries;
                }


                if (!tvSeries.isEmpty()) {
                    searchResultArea.setText(tvSeries);
                    searchResultArea.repaint();
                } else {
                    searchResultArea.setText("No TvSeries found");
                    searchResultArea.repaint();
                }

            }
        });


        tvSeriesSearchPanel.add(new JLabel("TvSeries"));
        tvSeriesSearchPanel.add(new JLabel("__________"));
        tvSeriesSearchPanel.add(new JLabel("Title:"));
        tvSeriesSearchPanel.add(titleField);

        tvSeriesSearchPanel.add(new JLabel("Genre:"));
        tvSeriesSearchPanel.add(genreField);

        tvSeriesSearchPanel.add(new JLabel("Description:"));
        tvSeriesSearchPanel.add(descriptionField);


        tvSeriesSearchPanel.add(new JLabel("Suitability:"));
        tvSeriesSearchPanel.add(suitabilityField);

        tvSeriesSearchPanel.add(new JLabel("Actors:"));
        tvSeriesSearchPanel.add(actorsField);

        tvSeriesSearchPanel.add(new JLabel("Release Year:"));
        tvSeriesSearchPanel.add(releaseYearField);

        tvSeriesSearchPanel.add(new JLabel("Related Series:"));
        tvSeriesSearchPanel.add(relatedTvSeriesField);

        tvSeriesSearchPanel.setLayout(new BoxLayout(tvSeriesSearchPanel, BoxLayout.Y_AXIS));

        tvSeriesSearchPanel.add(searchButton);

        tvSeriesSearchPanel.add(searchScrollPane);

        return tvSeriesSearchPanel;
    }


    JPanel subscriberPanel(String username) {

        JPanel searchPanel = new JPanel();
        searchPanel.setBackground(Color.LIGHT_GRAY);
        searchPanel.setSize(1500, 200);

        JTextField titleField = new JTextField(6);
        JTextField reviewTextField = new JTextField(6);
        JTextField ratingField = new JTextField(6);


        JButton searchButton = new JButton("Search Review");
        JTextArea searchResultArea = new JTextArea(10, 40);

        JScrollPane searchScrollPane = new JScrollPane(searchResultArea);


        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

                Review searchResults = repositorySubscriber.findReview(username, title);

                if (searchResults == null) {
                    searchResultArea.setText("There is no review for " + title);
                    searchResultArea.repaint();
                } else {
                    searchResultArea.setText(searchResults.toString());
                    searchResultArea.repaint();
                }
            }
        });

        JButton searchButtonFavorites = new JButton("Search Favorites");
        searchButtonFavorites.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

                Subscriber searchResults = repositorySubscriber.findSubscriber(username);
                searchResultArea.setText(searchResults.getFavoriteMovies().toString() + "\n" + searchResults.getFavoriteTvSeries());
                searchResultArea.repaint();
            }
        });


        JButton addFavoritesButton = new JButton("Add to favorites");
        addFavoritesButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

                Subscriber searchResults = repositorySubscriber.findSubscriber(username);

                List<Movie> movies = repositoryMovies.findMovies(title, "", "", "", "", List.of(""), "", List.of(""));
                List<TvSeries> series = repositoryTvSeries.findTvSeries(title, "", "", "", List.of(""), "", List.of(""));
                if (movies.isEmpty() && series.isEmpty()) {
                    searchResultArea.setText("No movie or Tv series exist with that name");
                    searchResultArea.repaint();
                } else if (!movies.isEmpty()) {
                    if (!searchResults.getFavoriteMovies().contains(title)) {
                        searchResults.getFavoriteMovies().add(movies.get(0).getTitle());
                        repositorySubscriber.updateSubscriber(username, searchResults);
                    }
                    searchResultArea.setText("Movie " + title + " was added to favorite.");
                    searchResultArea.repaint();
                } else {
                    if (!searchResults.getFavoriteTvSeries().contains(title)) {
                        searchResults.getFavoriteTvSeries().add(series.get(0).getTitle());
                        repositorySubscriber.updateSubscriber(username, searchResults);
                    }

                    searchResultArea.setText("Tv Series " + title + " was added to favorite.");
                    searchResultArea.repaint();
                }
            }
        });

        JButton addReviewButton = new JButton("Add review");
        addReviewButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String reviewText = reviewTextField.getText();


                if (title.isEmpty() || reviewText.isEmpty() || ratingField.getText().isEmpty()) {

                    searchResultArea.setText("There are empty fields review can not to be added");
                    searchResultArea.repaint();

                } else {
                    short rating = Short.parseShort(ratingField.getText());
                    Subscriber searchResults = repositorySubscriber.findSubscriber(username);
                    List<Movie> movies = repositoryMovies.findMovies(title, "", "", "", "", List.of(""), "", List.of(""));
                    List<TvSeries> series = repositoryTvSeries.findTvSeries(title, "", "", "", List.of(""), "", List.of(""));
                    if (movies.isEmpty() && series.isEmpty()) {
                        searchResultArea.setText("No movie or Tv series exist with that name");
                        searchResultArea.repaint();
                    } else {
                        int counter = 0;
                        for (Review review : searchResults.getReviews()) {
                            if (review.getTitle().equals(title)) {
                                review.setRating(rating);
                                review.setReviewText(reviewText);
                                counter = 1;
                            }
                        }
                        if (counter == 0) {
                            searchResults.getReviews().add(new Review(reviewText, rating, title));
                        }

                        repositorySubscriber.updateSubscriber(username, searchResults);
                        searchResultArea.setText("A new review for " + title + " was added");
                        searchResultArea.repaint();
                    }

                }
            }
        });


        searchPanel.add(new JLabel("My account: " + username));
        searchPanel.add(new JLabel("__________"));
        searchPanel.add(new JLabel("Title:"));
        searchPanel.add(titleField);
        searchPanel.add(new JLabel("Text:"));
        searchPanel.add(reviewTextField);
        searchPanel.add(new JLabel("Rating:"));
        searchPanel.add(ratingField);


        searchPanel.add(searchButton);
        searchPanel.add(searchButtonFavorites);
        searchPanel.add(addFavoritesButton);
        searchPanel.add(addReviewButton);
        searchPanel.add(searchScrollPane);


        searchPanel.setLayout(new

                BoxLayout(searchPanel, BoxLayout.Y_AXIS));
        return searchPanel;

    }
}
