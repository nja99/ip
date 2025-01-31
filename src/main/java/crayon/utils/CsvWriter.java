package crayon.utils;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CsvWriter {

    private final String filePath;

    public CsvWriter(String filePath) {
        this.filePath = filePath;
    }

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
