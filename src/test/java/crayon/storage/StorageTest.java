package crayon.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.io.TempDir;

import crayon.tasks.Task;

public class StorageTest {

    @TempDir
    Path tempDir;

    private Storage storage;
    private Path testFilePath;

    @BeforeEach
    void setUp() throws IOException {
        testFilePath = tempDir.resolve("test_tasks.csv");
        storage = new Storage(testFilePath.toString());
    }

    @Test
    void testSaveTasksToCsv() throws IOException {
        List<Task> tasks = new ArrayList<>();
        tasks.add(new TaskStub("todo", "Homework", false, new String[]{"todo", "false", "homework"}));
        tasks.add(new TaskStub("event", "Career Fair", false, new String[]{
                "event", "false", "Career Fair", "2025-02-07T14:00:00", "2025-02-07T15:00:00"}));
        tasks.add(new TaskStub("deadline", "Submit Report", false, new String[]{
                "deadline", "false", "Submit Report", "", "2025-02-08T14:00:00"}));


        // Save tasks
        storage.saveTasksToCsv(tasks);

        // Check File Exists
        assertTrue(testFilePath.toFile().exists());
        assertTrue(testFilePath.toFile().length() > 0);

        // Read file content directly
        List<String> lines = Files.readAllLines(testFilePath);
        assertEquals(4, lines.size()); // Ensure 1 header row and 3 tasks are saved.

        // Ensure data are saved correctly.
        assertEquals("todo,false,homework", lines.get(1));
        assertEquals("event,false,Career Fair,2025-02-07T14:00:00,2025-02-07T15:00:00", lines.get(2));
        assertEquals("deadline,false,Submit Report,,2025-02-08T14:00:00", lines.get(3));
    }

    @Test
    void testLoadTasksFromCsv() throws IOException {
        // Manually write test CSV data
        List<String> csvLines = List.of(
                "task,isDone,description,startDate,endDate",
                "todo,false,homework",
                "event,false,Career Fair,2025-02-07T14:00:00,2025-02-07T15:00:00",
                "deadline,false,Submit Report,,2025-02-08T14:00:00"
        );
        Files.write(testFilePath, csvLines);

        // Load tasks
        List<Task> loadedTasks = storage.loadTasksFromCsv();

        // Validate the loaded tasks
        assertEquals(3, loadedTasks.size());
        assertEquals("todo", loadedTasks.get(0).getType());
        assertEquals("event", loadedTasks.get(1).getType());
        assertEquals("deadline", loadedTasks.get(2).getType());
    }
}
