import tasks.Task;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class CSVWriter {

    private final String filePath;

    public CSVWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeToCSV(String[] header, List<Task> tasks) throws IOException {

        // Create file directories if they don't exist
        Path path = Paths.get(filePath);
        Files.createDirectories(path.getParent());

        try (FileWriter writer = new FileWriter(filePath)) {
            writer.write(String.join(",", header) + "\n");

            for(Task task : tasks) {
                writer.write(String.join(",", task.toCSVRow()) + "\n");
            }
        }

    }
}
