package auth.cableTv.gui;

import auth.cableTv.domain.Episode;
import auth.cableTv.domain.Movie;
import auth.cableTv.domain.Season;
import auth.cableTv.domain.TvSeries;
import auth.cableTv.repository.ObjectParser;
import auth.cableTv.repository.RepositoryMovies;
import auth.cableTv.repository.RepositoryTvSeries;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdminUi {

    private RepositoryMovies repositoryMovies = new RepositoryMovies();
    private RepositoryTvSeries repositoryTvSeries = new RepositoryTvSeries();

    private ObjectParser objectParser = new ObjectParser();


    void showAdminPanel(String username) {

        JFrame adminFrame = new JFrame("Admin Panel - " + username);
        adminFrame.setSize(1600, 600);
        adminFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        adminFrame.setLayout(new FlowLayout());
        adminFrame.add(searchPanelMovies());
        adminFrame.add(searchPanelTvSeries());
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
                List<String> relatedMoviesList = Arrays.asList(moviesArray);
                String[] actorsArray = actors.split(",");
                List<String> actorsList = Arrays.asList(actorsArray);

                List<Movie> searchResults = repositoryMovies.findMovies(title, description, genre, duration, suitability, actorsList, releaseYear, relatedMoviesList);

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

        JButton addButton = new JButton("Add");

        addButton.addActionListener(new ActionListener() {
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
                String[] moviesArray = relatedMovies.split(", ");
                List<String> relatedMoviesList = Arrays.asList(moviesArray);

                String[] actorsArray = actors.split(", ");
                List<String> actorsList = Arrays.asList(actorsArray);

                if (title.isEmpty() || genre.isEmpty() || description.isEmpty() || duration.isEmpty() || suitability.isEmpty() || actors.isEmpty() || releaseYear.isEmpty()) {
                    searchResultArea.setText("There are missing filed can not save movie");
                    searchResultArea.repaint();
                } else {
                    Movie movie = new Movie(title, genre, description, Boolean.parseBoolean(suitability), Integer.parseInt(releaseYear), actorsList, Integer.parseInt(duration), relatedMoviesList);

                    repositoryMovies.saveMovie(movie);

                    searchResultArea.setText("Movie " + movie.getTitle() + " was saved.");
                    searchResultArea.repaint();
                }
            }
        });

        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();

                Movie movie = new Movie();
                movie.setTitle(title);
                List<Movie> movies = repositoryMovies.findMovies(title, "", "", "", "", List.of(""), "", List.of(""));
                if (!movies.isEmpty()) {
                    repositoryMovies.deleteMovie(movie);
                    searchResultArea.setText("Movie " + movie.getTitle() + " was deleted.");
                    searchResultArea.repaint();
                } else {
                    searchResultArea.setText("Movie " + title + " was not found");
                    searchResultArea.repaint();
                }

            }
        });

        JButton updateButton = new JButton("Edit");

        updateButton.addActionListener(new ActionListener() {
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
                String[] moviesArray = relatedMovies.split(", ");
                List<String> relatedMoviesList = Arrays.asList(moviesArray);

                String[] actorsArray = actors.split(", ");
                List<String> actorsList = Arrays.asList(actorsArray);


                List<Movie> searchResults = repositoryMovies.findMovies(title, "", "", "", "", List.of(""), "", List.of(""));
                if (!searchResults.isEmpty()) {
                    Movie movie = searchResults.get(0);
                    if (genre != null) {
                        movie.setGenre(genre);
                    }
                    if (description != null) {
                        movie.setDescription(description);
                    }
                    if (duration != null) {
                        movie.setDuration(Integer.parseInt(duration));
                    }
                    if (suitability != null) {
                        movie.setSuitability(Boolean.parseBoolean(suitability));
                    }
                    if (!actors.isEmpty()) {
                        movie.setActors(actorsList);
                    }
                    if (!relatedMovies.isEmpty()) {
                        movie.setRelatedMovies(relatedMoviesList);
                    }
                    if (releaseYear != null) {
                        movie.setReleaseYear(Integer.parseInt(releaseYear));
                    }

                    repositoryMovies.updateMovie(title, movie);

                    searchResultArea.setText("Movie " + movie.getTitle() + " was edited.");
                    searchResultArea.repaint();
                } else {
                    searchResultArea.setText("Movie " + title + " was not found");
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

        moviesSearchPanel.add(addButton);

        moviesSearchPanel.add(deleteButton);

        moviesSearchPanel.add(updateButton);

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
                List<String> relatedTvSeriesList = Arrays.asList(seriesArray);

                String[] actorsArray = actors.split(", ");
                List<String> actorsList = Arrays.asList(actorsArray);

                List<TvSeries> searchResults = repositoryTvSeries.findTvSeries(title, description, genre, suitability, actorsList, releaseYear, relatedTvSeriesList);

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


        JButton addButton = new JButton("Add");

        JButton addSeasonButton = new JButton("Add Season");


        List<JTextField> seasonNumberFields = new ArrayList<>();
        List<JTextField> releaseYearFields = new ArrayList<>();
        List<JTextField> episodeFieldsList = new ArrayList<>();

        JPanel buttons = new JPanel();
        buttons.setBackground(Color.LIGHT_GRAY);


        addSeasonButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JPanel seasonPanel = new JPanel();
                JTextField seasonNumberField = new JTextField(5);
                JTextField releaseYearField = new JTextField(5);
                JTextField episodesField = new JTextField(5);

                seasonPanel.add(new JLabel("Season Number:"));
                seasonPanel.add(seasonNumberField);

                seasonPanel.add(new JLabel("Release Year:"));
                seasonPanel.add(releaseYearField);

                seasonPanel.add(new JLabel("Episodes:"));
                seasonPanel.add(episodesField);

                seasonNumberFields.add(seasonNumberField);
                releaseYearFields.add(releaseYearField);
                episodeFieldsList.add(episodesField);


                tvSeriesSearchPanel.add(seasonPanel);
                tvSeriesSearchPanel.revalidate();
                tvSeriesSearchPanel.repaint();
            }
        });


        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String genre = genreField.getText();
                String description = descriptionField.getText();
                String suitability = suitabilityField.getText(); // Assuming suitability is a boolean
                String actors = actorsField.getText();
                String releaseYear = releaseYearField.getText();
                String relatedTvSeries = relatedTvSeriesField.getText();
                if (title.isEmpty() || genre.isEmpty() || description.isEmpty() || suitability.isEmpty() || actors.isEmpty() || releaseYear.isEmpty() || seasonNumberFields.isEmpty() || releaseYearFields.isEmpty()) {
                    searchResultArea.setText("There are missing filed can not save TvSeries");
                    searchResultArea.repaint();
                } else {

                    String[] seriesArray = relatedTvSeries.split(", ");
                    List<String> relatedTvSeriesList = Arrays.asList(seriesArray);

                    String[] actorsArray = actors.split(", ");
                    List<String> actorsList = Arrays.asList(actorsArray);
                    List<Episode> episodesList = new ArrayList<>();
                    List<Season> seasonsList = new ArrayList<>();
                    for (int i = 0; i < seasonNumberFields.size(); i++) {

                        int seasonReleaseYear = Integer.parseInt(releaseYearFields.get(i).getText());
                        int seasonNumber = Integer.parseInt(seasonNumberFields.get(i).getText());
                        episodesList = objectParser.parseEpisodeList(episodeFieldsList.get(i).getText());

                        Season season = new Season();
                        season.setSeasonNumber(seasonNumber);
                        season.setReleaseYear(seasonReleaseYear);
                        season.setEpisodes(episodesList);
                        seasonsList.add(season);

                    }
                    if (episodesList.isEmpty()) {
                        searchResultArea.setText("Episode text must have format like this: [Episode(episodeNumber=1, duration=25), Episode(episodeNumber=2, duration=22)])]");
                        searchResultArea.repaint();
                    } else {
                        TvSeries tvSeries = new TvSeries(title, genre, description, Boolean.parseBoolean(suitability), Integer.parseInt(releaseYear), actorsList, seasonsList, relatedTvSeriesList);
                        repositoryTvSeries.saveTvSeries(tvSeries);
                        searchResultArea.setText("Series " + title + " was saved.");
                        searchResultArea.repaint();
                    }
                }
            }
        });


        JButton deleteButton = new JButton("Delete");

        deleteButton.addActionListener(new ActionListener() {
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
                List<String> relatedTvSeriesList = Arrays.asList(seriesArray);

                String[] actorsArray = actors.split(", ");
                List<String> actorsList = Arrays.asList(actorsArray);

                List<TvSeries> searchResults = repositoryTvSeries.findTvSeries(title, description, genre, suitability, actorsList, releaseYear, relatedTvSeriesList);
                if (searchResults.isEmpty()) {
                    searchResultArea.setText("Can not find TvSeries " + title);
                    searchResultArea.repaint();

                } else {
                    TvSeries tvSeries = new TvSeries();
                    tvSeries.setTitle(title);

                    repositoryTvSeries.deleteTvSeries(tvSeries);
                    searchResultArea.setText("Series " + tvSeries.getTitle() + " was deleted.");
                    searchResultArea.repaint();
                }
            }
        });


        JButton updateButton = new JButton("Edit");

        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String title = titleField.getText();
                String genre = genreField.getText();
                String description = descriptionField.getText();
                String suitability = suitabilityField.getText(); // Assuming suitability is a boolean
                String actors = actorsField.getText();
                String releaseYear = releaseYearField.getText();
                String relatedTvSeries = relatedTvSeriesField.getText();


                List<String> relatedTvSeriesList = new ArrayList<>();
                if (!relatedTvSeries.isEmpty()) {
                    String[] seriesArray = relatedTvSeries.split(", ");
                    relatedTvSeriesList = Arrays.asList(seriesArray);
                }

                List<String> actorsList = new ArrayList<>();
                if (!actors.isEmpty()) {
                    String[] actorsArray = actors.split(", ");
                    actorsList = Arrays.asList(actorsArray);
                }

                List<TvSeries> searchResults = repositoryTvSeries.findTvSeries(title, "", "", "", List.of(""), "", List.of(""));
                if (searchResults.isEmpty()) {
                    searchResultArea.setText("Can not find TvSeries " + title);
                    searchResultArea.repaint();

                } else {
                    TvSeries tvSeries = searchResults.get(0);

                    if (!genre.isEmpty()) {
                        tvSeries.setGenre(genre);
                    }
                    if (!description.isEmpty()) {
                        tvSeries.setDescription(description);
                    }
                    if (!suitability.isEmpty()) {
                        tvSeries.setSuitability(Boolean.parseBoolean(suitability));
                    }
                    if (!actorsList.isEmpty()) {
                        tvSeries.setActors(actorsList);
                    }
                    if (!relatedTvSeriesList.isEmpty()) {
                        tvSeries.setRelatedSeries(relatedTvSeriesList);
                    }
                    if (!releaseYear.isEmpty()) {
                        tvSeries.setReleaseYear(Integer.parseInt(releaseYear));
                    }


                    List<Season> seasonsList = new ArrayList<>();
                    for (int i = 0; i < seasonNumberFields.size(); i++) {
                        if (!seasonNumberFields.get(i).getText().isEmpty()) {
                            int seasonReleaseYear = Integer.parseInt(releaseYearFields.get(i).getText());
                            int seasonNumber = Integer.parseInt(seasonNumberFields.get(i).getText());
                            List<Episode> episodesList = objectParser.parseEpisodeList(episodeFieldsList.get(i).getText());
                            Season season = new Season();
                            season.setSeasonNumber(seasonNumber);
                            season.setReleaseYear(seasonReleaseYear);
                            season.setEpisodes(episodesList);
                            seasonsList.add(season);
                        }
                    }
                    if (!seasonsList.isEmpty()) {
                        tvSeries.setSeasons(seasonsList);
                    }

                    repositoryTvSeries.updateTvSeries(title, tvSeries);

                    searchResultArea.setText("TvSeries " + tvSeries.getTitle() + " was edited.");
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
        buttons.add(addButton);
        buttons.add(addSeasonButton);
        tvSeriesSearchPanel.add(searchButton);
        tvSeriesSearchPanel.add(buttons);
        tvSeriesSearchPanel.add(deleteButton);
        tvSeriesSearchPanel.add(updateButton);
        tvSeriesSearchPanel.add(searchScrollPane);

        return tvSeriesSearchPanel;
    }

}
