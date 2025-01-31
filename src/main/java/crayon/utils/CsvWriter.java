package crayon.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

/**
 * Represents a CSV writer to write to a CSV file.
 */
public class CsvWriter {

    private final String filePath;

    /**
     * Constructs a CSVWriter.
     *
     * @param filePath The file path of the CSV file.
     */
    public CsvWriter(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Writes to the CSV file.
     *
     * @param rows The rows to write to the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public void writeToCsv(List<String[]> rows) throws IOException {

        // Create file directories if they don't exist
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());

        try (FileWriter writer = new FileWriter(filePath)) {
            for (String[] row : rows) {
                writer.write(String.join(",", row) + "\n");
            }
        }

    }
}
