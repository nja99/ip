package crayon.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
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

    @BeforeEach
    void setUp() {
        Path testFilePath = tempDir.resolve("test_tasks.csv");
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

        storage.saveTasksToCsv(tasks);

        List<Task> loadedTasks = storage.loadTasksFromCsv();
        assertEquals(tasks.size(), loadedTasks.size());
        assertEquals("todo", loadedTasks.get(0).getType());
        assertEquals("event", loadedTasks.get(1).getType());
        assertEquals("deadline", loadedTasks.get(2).getType());
    }

}
