package auth.cableTv.repository;

import auth.cableTv.domain.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaRepositoryImpl implements MediaRepository {

    private static final String JDBC_URL = "jdbc:sqlite:cableTv.db";

    /**
     * Saves a Media object to the database.
     *
     * @param media The Media object to be saved.
     * @return The auto-generated ID of the saved Media object.
     * @throws SQLException If an error occurs while saving the Media object.
     */
    public int saveMedia(Media media) {
        int id = 0;
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement statement = connection.prepareStatement(
                    "INSERT INTO Media (title, description, suitability, releaseYear,actors) VALUES (?, ?, ?, ?,?)",
                    Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, media.getTitle());
            statement.setString(2, media.getDescription());
            statement.setBoolean(3, media.isSuitability());
            statement.setInt(4, media.getReleaseYear());
            statement.setString(5, media.getActors().toString());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating media failed, no rows affected.");
            }

            // Retrieve the last inserted ID
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    media.setId(generatedKeys.getInt(1));
                    System.out.println("Generated Media ID: " + media.getId());
                    id = media.getId();
                } else {
                    throw new SQLException("Creating media failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }


    /**
     * Retrieves a Media object from the database based on the provided media ID.
     *
     * @param mediaId The ID of the Media object to be retrieved.
     * @return The Media object corresponding to the provided ID, or null if not found.
     * @throws SQLException If an error occurs while retrieving the Media object.
     */
    public Media getMediaById(int mediaId) {
        Media media = null;
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Media WHERE id = ?");

            statement.setInt(1, mediaId);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    media = mapResultSetToMedia(resultSet);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return media;
    }

    /**
     * Retrieves a list of Media objects from the database based on the provided title.
     *
     * @param title The title of the Media objects to be retrieved.
     * @return A list of Media objects with the provided title, or an empty list if none are found.
     * @throws SQLException If an error occurs while retrieving the Media objects.
     */
    public List<Media> getMediaByTitle(String title) {
        List<Media> mediaList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);

            PreparedStatement statement = connection.prepareStatement("SELECT * FROM Media WHERE title = ?");

            statement.setString(1, title);

            try (ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    mediaList.add(mapResultSetToMedia(resultSet));
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mediaList;
    }

    /**
     * Retrieves a list of Media objects from the database based on the provided actor.
     *
     * @param actor The actor's name to search for in the list of Media objects.
     * @return A list of Media objects that include the provided actor, or an empty list if none are found.
     * @throws SQLException If an error occurs while retrieving the Media objects.
     */
    public List<Media> getMediaByActor(String actor) {
        List<Media> result = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement statement = connection.prepareStatement(JDBC_URL);

            statement.setString(1, "%" + actor + "%");

            try {
                ResultSet resultSet = statement.executeQuery();


                while (resultSet.next()) {
                    Media media = new Media();
                    media.setId(resultSet.getInt("id"));
                    media.setTitle(resultSet.getString("title"));
                    media.setDescription(resultSet.getString("description"));
                    media.setSuitability(resultSet.getBoolean("suitability"));
                    media.setReleaseYear(resultSet.getInt("releaseYear"));
                    media.setActors(getListFromString(resultSet.getString("actors")));

                    result.add(media);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        return result;
    }

    /**
     * Retrieves a list of all Media objects from the database.
     *
     * @return A list of all Media objects stored in the database, or an empty list if none are found.
     * @throws SQLException If an error occurs while retrieving the Media objects.
     */
    public List<Media> getAllMedia() {
        List<Media> mediaList = new ArrayList<>();

        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Media");

            while (resultSet.next()) {
                Media media = mapResultSetToMedia(resultSet);
                mediaList.add(media);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return mediaList;
    }


    /**
     * Updates the details of a media record in the database based on the provided media information.
     *
     * @param media The Media object containing the updated information.
     */
    public void updateMedia(Media media) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement statement = connection.prepareStatement(
                    "UPDATE Media SET title = ?, description = ?, suitability = ?, releaseYear = ? WHERE id = ?");

            statement.setString(1, media.getTitle());
            statement.setString(2, media.getDescription());
            statement.setBoolean(3, media.isSuitability());
            statement.setInt(4, media.getReleaseYear());
            statement.setInt(5, media.getId());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Updating media failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a media record from the database based on the provided media ID.
     *
     * @param mediaId The ID of the media record to be deleted.
     */
    public void deleteMedia(int mediaId) {
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            PreparedStatement statement = connection.prepareStatement("DELETE FROM Media WHERE id = ?");

            statement.setInt(1, mediaId);

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Deleting media failed, no rows affected.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    /**
     * Maps a ResultSet to a Media object.
     *
     * @param resultSet The ResultSet containing media information.
     * @return A Media object populated with data from the ResultSet.
     * @throws SQLException If an error occurs while mapping the ResultSet to a Media object.
     */
    private Media mapResultSetToMedia(ResultSet resultSet) throws SQLException {
        Media media = new Media();
        media.setId(resultSet.getInt("id"));
        media.setTitle(resultSet.getString("title"));
        media.setDescription(resultSet.getString("description"));
        media.setSuitability(resultSet.getBoolean("suitability"));
        media.setReleaseYear(resultSet.getInt("releaseYear"));
        media.setActors(getListFromString(resultSet.getString("actors")));
        return media;
    }

    /**
     * Converts a comma-separated string enclosed in square brackets to a list of strings.
     *
     * @param inputString The input string containing a comma-separated list of strings enclosed in square brackets.
     * @return A List of strings extracted from the input string.
     */
    private static List<String> getListFromString(String inputString) {
        String[] actorsArray = inputString.substring(1, inputString.length() - 1).split(", ");

        // Convert array to list
        List<String> actorsList = new ArrayList<>();
        for (String actor : actorsArray) {
            actorsList.add(actor);
        }

        return actorsList;
    }
}
