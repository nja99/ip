package crayon;

import crayon.enums.TaskType;
import crayon.exceptions.CrayonInvalidTaskIdException;
import crayon.exceptions.CrayonIllegalArgumentException;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.tasks.*;

import java.util.ArrayList;
import java.util.List;

/**
 * This class represents a list of tasks.
 */
public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task";
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done";
    private static final String TASK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet";

    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {}

    /**
     * Constructs a new TaskList instance with the specified tasks.
     *
     * @param tasks The tasks to add to the list.
     */
    public TaskList(List<Task> tasks){
        this.tasks.addAll(tasks);
    }

    private void validateTaskId(int taskId) throws CrayonIllegalArgumentException {
        if (tasks.isEmpty()) {
            throw new CrayonIllegalArgumentException("No tasks available to perform this action.");
        }

        if (taskId < 1 || taskId >= tasks.size() + 1) {
            throw new CrayonInvalidTaskIdException("Invalid TaskID! Please enter a number between 1 - " + (tasks.size() + 1));
        }
    }

    /**
     * Creates a new task and adds it to the list.
     *
     * @param taskType The type of task to create.
     * @param description The description of the task.
     * @return The message indicating the task has been added.
     */
    public String createTask(TaskType taskType, String description) {
        try {
            Task task = switch (taskType) {
                case TODO -> ToDo.createToDoTask(description);
                case DEADLINE -> Deadline.createDeadlineTask(description);
                case EVENT -> Event.createEventTask(description);
            };

            tasks.add(task);
            return formatTaskAction(task, TASK_ADDED_MESSAGE);
        } catch (CrayonInvalidFormatException e) {
            return e.getMessage();
        }
    }

    /**
     * Retrieves the lsit of tasks
     *
     * @return A list containing all the tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists all the tasks in the list.
     *
     * @return The message containing all the tasks in the list.
     */
    public String listTasks() {
        int counter = 1;

        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append("Here are the tasks in your list:\n");
        for(Task task : tasks) {
            sb.append("    ").append(counter).append(".").append(task).append("\n");
            counter++;
        }
        sb.append(Constants.SEPARATOR);
        return sb.toString();
    }

    /**
     * Deletes a task from the list.
     *
     * @param taskId The ID of the task to delete.
     * @return The message indicating the task has been deleted.
     */
    public String deleteTask(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        return formatTaskAction(task, TASK_REMOVED_MESSAGE);

    }

    /**
     * Marks a task as done.
     *
     * @param taskId The ID of the task to mark as done.
     * @return The message indicating the task has been marked as done.
     */
    public String markTaskAsDone(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markDone();

        return formatStatusAction(task, TASK_DONE_MESSAGE);
    }

    /**
     * Marks a task as undone.
     *
     * @param taskId The ID of the task to mark as undone.
     * @return The message indicating the task has been marked as undone.
     */
    public String markTaskAsUndone (int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markUndone();

        return formatStatusAction(task, TASK_UNDONE_MESSAGE);
    }

    private String formatStatusAction(Task task, String message) {
        return message + "\n    " + task + "\n";
    }

    private String formatTaskAction(Task task, String message) {
        return message + "\n    " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in your list\n";
    }
}
