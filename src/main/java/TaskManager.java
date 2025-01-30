import enums.TaskType;
import exceptions.CrayonInvalidTaskIdException;
import exceptions.CrayonIllegalArgumentException;
import exceptions.CrayonInvalidFormatException;
import tasks.*;
import utils.CSVReader;
import utils.CSVWriter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {

    private static final List<Task> tasks = new ArrayList<>();
    private static final String DEFAULT_PATH = "./data/tasks.csv";
    private static final String[] DEFAULT_HEADER = {"task", "isDone", "description", "startDate", "endDate"};

    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task";
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done";
    private static final String TASK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet";

    private static void validateTaskId(int taskId) throws CrayonIllegalArgumentException {
        if (tasks.isEmpty()) {
            throw new CrayonIllegalArgumentException("No tasks available to perform this action.");
        }

        if (taskId < 1 || taskId >= tasks.size() + 1) {
            throw new CrayonInvalidTaskIdException("Invalid TaskID! Please enter a number between 1 - " + (tasks.size() + 1));
        }
    }

    /**
     * Creates a task of the specified type and adds it to the task list.
     * @param taskType The type of task to create.
     * @param description A brief description of the task.
     */
    public static void createTask(TaskType taskType, String description) {
        try {
            Task task = switch (taskType) {
                case TODO -> ToDo.createToDoTask(description);
                case DEADLINE -> Deadline.createDeadlineTask(description);
                case EVENT -> Event.createEventTask(description);
            };

            addTask(task);
        } catch (CrayonInvalidFormatException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void writeTasksToCSV() throws IOException {
        CSVWriter writer = new CSVWriter(DEFAULT_PATH);
        writer.writeToCSV(DEFAULT_HEADER, tasks);
    }

    public static void readTasksFromCSV() throws IOException {
        CSVReader reader = new CSVReader(DEFAULT_PATH);
        List<Task> loadedTask = reader.readFromCSV();
        tasks.clear();
        tasks.addAll(loadedTask);
    }

    /**
     * Lists all tasks
     */
    public static void listTasks() {
        int counter = 1;

        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append("Here are the tasks in your list:\n");
        for(Task task : tasks) {
            sb.append("    ").append(counter).append(".").append(task).append("\n");
            counter++;
        }
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }

    private static void addTask(Task taskDescription) {
        tasks.add(taskDescription);
        printTaskAction(taskDescription, TASK_ADDED_MESSAGE);
    }

    public static void deleteTask(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        printTaskAction(task, TASK_REMOVED_MESSAGE);

    }

    public static void markTaskAsDone(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markDone();

        printStatusAction(task, TASK_DONE_MESSAGE);
    }

    public static void markTaskAsUndone (int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markUndone();

        printStatusAction(task, TASK_UNDONE_MESSAGE);
    }

    private static void printStatusAction(Task task, String message) {
        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append(message).append("\n    ");
        sb.append(task).append("\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }

    private static void printTaskAction(Task task, String message) {
        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append(message).append("\n    ");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }
}
