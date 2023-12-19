package auth.cableTv.repository;

import auth.cableTv.domain.Movie;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Repository {

    private static final String MOVIE_FILE_PATH = "movies.txt";

    public void saveLine(String file, String line) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file, true))) {
            writer.write(line);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    public void updateLineByString(String file, String searchString, String updatedString) {
        try {
            // Create a temporary file
            File tempFile = new File(file + ".tmp");

            // Open the original file in read mode
            BufferedReader reader = new BufferedReader(new FileReader(file));

            // Create a writer for the temporary file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // Read each line from the original file, modify if necessary, and write to the temporary file
            String line;
            while ((line = reader.readLine()) != null) {
                if (line.contains(searchString)) {
                    // Modify the line with the updated string
                    line = updatedString;
                }
                writer.write(line);
                writer.newLine();
            }

            // Close the reader and writer
            reader.close();
            writer.close();

            if (!new File(file).delete()) {
                throw new IOException("Failed to delete the original file.");
            }
//         Replace the original file with the temporary file
            if (tempFile.renameTo(new File(file))) {
                System.out.println("String modification successful.");
            } else {
                System.out.println("Failed to replace the original file with the temporary file.");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteLineByString(String filePath, String lineToDelete) {
        try {
            // Create a temporary file
            File tempFile = new File(filePath + ".tmp");

            // Open the original file in read mode
            BufferedReader reader = new BufferedReader(new FileReader(filePath));

            // Create a writer for the temporary file
            BufferedWriter writer = new BufferedWriter(new FileWriter(tempFile));

            // Read each line from the original file, skip the line to delete, and write to the temporary file
            String line;
            while ((line = reader.readLine()) != null) {
                if (!line.contains(lineToDelete)) {
                    writer.write(line);
                    writer.newLine();
                }
            }

            // Close the reader and writer
            reader.close();
            writer.close();

            // Delete the original file
            if (!new File(filePath).delete()) {
                throw new IOException("Failed to delete the original file.");
            }

            // Rename the temporary file to the original name
            if (!tempFile.renameTo(new File(filePath))) {
                throw new IOException("Failed to rename the temporary file to the original file.");
            }

            System.out.println("Line deletion successful.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public List<String> getLines(String file, List<String> searchStrings) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = reader.readLine()) != null) {
                if (containsString(searchStrings, line)) {
                    lines.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return lines;
    }

    private static boolean containsString(List<String> stringList, String line) {

        for (String str : stringList) {

            if (!line.contains(str)) {
                return false;
            }
        }
        return true;
    }




}
