package crayon;

import crayon.enums.TaskType;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.tasks.Task;
import crayon.utils.CSVReader;
import crayon.utils.CSVWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static crayon.tasks.Deadline.createDeadlineFromCSV;
import static crayon.tasks.Event.createEventFromCSV;
import static crayon.tasks.ToDo.createToDoFromCSV;

/**
 * This class is responsible for loading and saving tasks to a CSV file.
 */
public class Storage {

    private static final String DEFAULT_PATH = "./data/tasks.csv";
    private static final String[] DEFAULT_HEADER = {"task", "isDone", "description", "startDate", "endDate"};

    /**
     * Saves the tasks to a CSV file.
     *
     * @param tasks The tasks to save.
     * @throws IOException If an I/O error occurs.
     */
    public void saveTasksToCSV(List<Task> tasks) throws IOException {
        List<String[]> rows = new ArrayList<>();
        rows.add(DEFAULT_HEADER);
        for (Task task : tasks) {
            rows.add(task.toCSVRow());
        }
        CSVWriter writer = new CSVWriter(DEFAULT_PATH);
        writer.writeToCSV(rows);
    }

    /**
     * Loads the tasks from a CSV file.
     *
     * @return The tasks loaded from the CSV file.
     * @throws IOException If an I/O error occurs.
     */
    public List<Task> loadTasksFromCSV() throws IOException {

        CSVReader reader = new CSVReader(DEFAULT_PATH);
        List<String[]> rows = reader.readFromCSV();

        List<Task> tasks = new ArrayList<>();

        // Skip header row
        for (int i = 1; i < rows.size(); i++) {
            String[] values = rows.get(i);
            TaskType taskType = TaskType.fromString(values[0]);
            try {
                Task task = switch(taskType) {
                    case TODO -> createToDoFromCSV(values);
                    case EVENT -> createEventFromCSV(values);
                    case DEADLINE -> createDeadlineFromCSV(values);
                };
                tasks.add(task);
            } catch (CrayonInvalidFormatException e) {
                System.out.println(e.getMessage());
            }
        }
        return tasks;
    }
}
