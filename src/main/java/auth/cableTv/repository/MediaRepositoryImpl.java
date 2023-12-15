package auth.cableTv.repository;

import auth.cableTv.domain.Media;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MediaRepositoryImpl implements MediaRepository {

    private static final String JDBC_URL = "jdbc:sqlite:cableTv.db";

    public int saveMedia(Media media) {
        int id = 0;
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);

             PreparedStatement statement = connection.prepareStatement(
                     "INSERT INTO Media (title, description, suitability, releaseYear) VALUES (?, ?, ?, ?)",
                     Statement.RETURN_GENERATED_KEYS);

            statement.setString(1, media.getTitle());
            statement.setString(2, media.getDescription());
            statement.setBoolean(3, media.isSuitability());
            statement.setInt(4, media.getReleaseYear());

            int affectedRows = statement.executeUpdate();

            if (affectedRows == 0) {
                throw new SQLException("Creating media failed, no rows affected.");
            }
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    media.setId(generatedKeys.getInt(1));
                    System.out.println("Generated Media ID: " + media.getId());
                   id= media.getId();
                } else {
                    throw new SQLException("Creating media failed, no ID obtained.");
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

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

    private Media mapResultSetToMedia(ResultSet resultSet) throws SQLException {
        Media media = new Media();
        media.setId(resultSet.getInt("id"));
        media.setTitle(resultSet.getString("title"));
        media.setDescription(resultSet.getString("description"));
        media.setSuitability(resultSet.getBoolean("suitability"));
        media.setReleaseYear(resultSet.getInt("releaseYear"));
        return media;
    }
}
