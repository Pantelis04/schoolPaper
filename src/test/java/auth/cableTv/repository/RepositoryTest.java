package auth.cableTv.repository;

import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class RepositoryTest extends TestCase {

    public void testSaveLine() {
        String lineToSave = "Movie(title=Test Movie, description=my movie, suitability=true, releaseYear=1992, actors=[Maria, Giorgos Markou], duration=104, genre=Sci-Fi, relatedMovies=[One supon a time in Hollywood, Batman])";
        Repository repository = new Repository();
        repository.saveLine("movies.txt",lineToSave);

        List<String> result = repository.getLines("movies.txt", List.of("Test Movie"));
        assertEquals(result.get(0),lineToSave);

        repository.deleteLineByString("movies.txt",lineToSave);


    }

    public void testUpdateLineByString() {


        String lineToSave = "Movie(title=Test Movie, description=my movie, suitability=true, releaseYear=1992, actors=[Maria, Giorgos Markou], duration=104, genre=Sci-Fi, relatedMovies=[One supon a time in Hollywood, Batman])";
        Repository repository = new Repository();
        repository.saveLine("movies.txt",lineToSave);


        String lineToUpdate = "Movie(title=Test Movie, description=changed, suitability=true, releaseYear=1992, actors=[Maria, Giorgos Markou], duration=104, genre=Sci-Fi, relatedMovies=[One supon a time in Hollywood, Batman])";
        repository.updateLineByString("movies.txt","Test Movie",lineToUpdate);


        List<String> result = repository.getLines("movies.txt", List.of("Test Movie"));
        assertEquals(result.get(0),lineToUpdate);

        repository.deleteLineByString("movies.txt",lineToUpdate);
    }

    public void testDeleteLineByString() {
        String lineToSave = "Movie(title=Test Movie, description=my movie, suitability=true, releaseYear=1992, actors=[Maria, Giorgos Markou], duration=104, genre=Sci-Fi, relatedMovies=[One supon a time in Hollywood, Batman])";
        Repository repository = new Repository();
        repository.saveLine("movies.txt",lineToSave);

        repository.deleteLineByString("movies.txt",lineToSave);

        List<String> result = repository.getLines("movies.txt", List.of("Test Movie"));
        assertEqual(result,new ArrayList<>());
    }



    public void testGetLines() {
        String lineToSave = "Movie(title=Test Movie, description=my movie, suitability=true, releaseYear=1992, actors=[Maria, Giorgos Markou], duration=104, genre=Sci-Fi, relatedMovies=[One supon a time in Hollywood, Batman])";
        Repository repository = new Repository();
        repository.saveLine("movies.txt",lineToSave);

        List<String> result = repository.getLines("movies.txt", List.of("Test Movie"));
        assertEqual(result.get(0),lineToSave);

        repository.deleteLineByString("movies.txt",lineToSave);

    }


    private static void assertEqual(Object expected, Object actual) {
        if (!expected.equals(actual)) {
            throw new AssertionError("Expected: " + expected + ", Actual: " + actual);
        }
    }
}