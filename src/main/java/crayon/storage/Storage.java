package crayon.storage;

import static crayon.tasks.Deadline.createDeadlineFromCsv;
import static crayon.tasks.Event.createEventFromCsv;
import static crayon.tasks.ToDo.createToDoFromCsv;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import crayon.enums.TaskType;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.tasks.Task;
import crayon.utils.CsvReader;
import crayon.utils.CsvWriter;

/**
 * This class is responsible for loading and saving tasks to a CSV file.
 */
public class Storage {

    private static final String DEFAULT_PATH = "./data/tasks.csv";
    private static final String[] DEFAULT_HEADER = {"task", "isDone", "description", "startDate", "endDate"};

    private final String filePath;

    public Storage(String filePath) {
        assert filePath != null : "File path cannot be null";
        assert !filePath.isEmpty() : "File path cannot be empty";
        this.filePath = filePath;
    }

    public Storage() {
        this(DEFAULT_PATH);
    }

    /**
     * Saves the tasks to a CSV file.
     *
     * @param tasks The tasks to save.
     */
    public boolean saveTasksToCsv(List<Task> tasks) {

        assert tasks != null : "Tasks List cannot be null";
        assert !tasks.contains(null) : "Tasks List cannot contain null task";

        try {
            List<String[]> rows = new ArrayList<>();
            rows.add(DEFAULT_HEADER);
            for (Task task : tasks) {
                rows.add(task.toCsvRow());
            }
            CsvWriter writer = new CsvWriter(filePath);
            writer.writeToCsv(rows);
            return true;
        } catch (IOException e) {
            System.out.println("Error saving tasks to file" + e.getMessage());
            return false;
        }
    }

    /**
     * Loads the tasks from a CSV file.
     *
     * @return The tasks loaded from the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public List<Task> loadTasksFromCsv() throws IOException {

        CsvReader reader = new CsvReader(filePath);
        List<String[]> rows = reader.readFromCsv();

        List<Task> tasks = new ArrayList<>();

        // Skip header row
        for (int i = 1; i < rows.size(); i++) {
            String[] values = rows.get(i);
            assert values.length == DEFAULT_HEADER.length : "CSV row does not contain expected number of columns";

            TaskType taskType = TaskType.fromString(values[0]);
            try {
                Task task = switch(taskType) {
                case TODO -> createToDoFromCsv(values);
                case EVENT -> createEventFromCsv(values);
                case DEADLINE -> createDeadlineFromCsv(values);
                };
                tasks.add(task);
            } catch (CrayonInvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }
}
