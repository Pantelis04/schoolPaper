package auth.cableTv;

import auth.cableTv.domain.Media;
import auth.cableTv.repository.MediaRepository;
import auth.cableTv.repository.MediaRepositoryImpl;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * Hello world!
 */
public class App {
    private static final String JDBC_URL = "jdbc:sqlite:cableTv.db";

    public static void main(String[] args) {
        createTables();
    }

    private static void createTables() {

      List<String> p=new ArrayList<>();
        try {
            Connection connection = DriverManager.getConnection(JDBC_URL);
            Statement statement = connection.createStatement();

            // Create Media table
            statement.execute("CREATE TABLE IF NOT EXISTS Media (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "title TEXT," +
                    "description TEXT," +
                    "suitability INTEGER," +
                    "releaseYear INTEGER," +
                    "actors TEXT" +
                    ")");

            // Create Movie table
            statement.execute("CREATE TABLE IF NOT EXISTS Movie (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "duration INTEGER," +
                    "genre TEXT," +
                    "media_id INTEGER," +
                    "FOREIGN KEY (media_id) REFERENCES Media(id)" +
                    ")");

            // Create Episode table
            statement.execute("CREATE TABLE IF NOT EXISTS Episode (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "episodeNumber INTEGER," +
                    "duration INTEGER," +
                    "season_id INTEGER," +
                    "FOREIGN KEY (season_id) REFERENCES Season(id)" +
                    ")");

            // Create Review table
            statement.execute("CREATE TABLE IF NOT EXISTS Review (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "reviewText TEXT," +
                    "rating INTEGER" +
                    ")");

            // Create Season table
            statement.execute("CREATE TABLE IF NOT EXISTS Season (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "seasonNumber INTEGER," +
                    "releaseYear INTEGER," +
                    "tvSeries_id INTEGER," +
                    "FOREIGN KEY (tvSeries_id) REFERENCES TvSeries(id)" +
                    ")");

            // Create Subscriber table
            statement.execute("CREATE TABLE IF NOT EXISTS Subscriber (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "username TEXT," +
                    "password TEXT" +
                    ")");

            // Create TvSeries table
            statement.execute("CREATE TABLE IF NOT EXISTS TvSeries (" +
                    "id INTEGER PRIMARY KEY AUTOINCREMENT," +
                    "category TEXT," +
                    "media_id INTEGER," +
                    "FOREIGN KEY (media_id) REFERENCES Media(id)" +
                    ")");

            // Create FavoriteMedia table
            statement.execute("CREATE TABLE IF NOT EXISTS FavoriteMedia (" +
                    "subscriber_id INTEGER," +
                    "media_id INTEGER," +
                    "PRIMARY KEY (subscriber_id, media_id)," +
                    "FOREIGN KEY (subscriber_id) REFERENCES Subscriber(id)," +
                    "FOREIGN KEY (media_id) REFERENCES Media(id)" +
                    ")");

            // Create ReviewMedia table
            statement.execute("CREATE TABLE IF NOT EXISTS ReviewMedia (" +
                    "review_id INTEGER," +
                    "media_id INTEGER," +
                    "PRIMARY KEY (review_id, media_id)," +
                    "FOREIGN KEY (review_id) REFERENCES Review(id)," +
                    "FOREIGN KEY (media_id) REFERENCES Media(id)" +
                    ")");

            System.out.println("Tables created successfully.");

            // Insert data into Media table
            statement.execute("INSERT INTO Media (title, description, suitability, releaseYear, actors) VALUES" +
                    "('Inception', 'A mind-bending thriller', 1, 2010, '[Leonardo DiCaprio, Joseph Gordon-Levitt]')," +
                    "('The Shawshank Redemption', 'Drama in prison', 0, 1994, '[Tim Robbins, Morgan Freeman]')," +
                    "('The Godfather', 'Mafia epic', 0, 1972, '[Marlon Brando, Al Pacino]')," +
                    "('Pulp Fiction', 'Quentin Tarantino classic', 1, 1994, '[John Travolta, Uma Thurman]')," +
                    "('The Dark Knight', 'Batman battles Joker', 1, 2008, '[Christian Bale, Heath Ledger]')," +
                    "('Stranger Things', 'Mystery in Hawkins', 1, 2016, '[Winona Ryder, David Harbour]')," +
                    "('Breaking Bad', 'Meth and morality', 0, 2008, '[Bryan Cranston, Aaron Paul]')," +
                    "('Friends', 'Classic sitcom', 1, 1994, '[Jennifer Aniston, Courteney Cox]')," +
                    "('The Crown', 'Royalty and politics', 1, 2016, '[Claire Foy, Matt Smith]')," +
                    "('Game of Thrones', 'Epic fantasy', 0, 2011, '[Emilia Clarke, Kit Harington]');");

            // Insert data into Movie table
            statement.execute("INSERT INTO Movie (duration, genre, media_id) VALUES" +
                    "(148, 'Sci-Fi', 1)," +
                    "(142, 'Drama', 2)," +
                    "(175, 'Crime', 3)," +
                    "(154, 'Crime', 4)," +
                    "(152, 'Action', 5)");

            // Insert data into TvSeries table
            statement.execute("INSERT INTO TvSeries (category, media_id) VALUES" +
                    "('Science Fiction', 6)," +
                    "('Drama', 7)," +
                    "('Comedy', 8)," +
                    "('Comedy', 9)," +
                    "('Drama', 10)");

            // Insert data into Episode table
            statement.execute("INSERT INTO Episode (episodeNumber, duration, season_id) VALUES" +
                    "(1, 50, 1)," +
                    "(1, 45, 2)," +
                    "(1, 60, 3)," +
                    "(1, 22, 4)," +
                    "(1, 55, 5)");

            System.out.println("Data inserted successfully.");

            Media media = new Media();
            media.setId(50);
            media.setTitle("Rambo");
            media.setDescription("Soldiers fighting");
            media.setReleaseYear(1990);
            media.setSuitability(true);
            media.setActors(List.of("Stalone","Britney"));

            MediaRepository mediaRepository = new MediaRepositoryImpl();
            int id = mediaRepository.saveMedia(media);

            System.out.println(mediaRepository.getMediaById(id).getActors());
            System.out.println(mediaRepository.getMediaById(1).getActors());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}






