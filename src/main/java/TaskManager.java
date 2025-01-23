import exceptions.CrayonIllegalArgumentException;
import tasks.Task;

import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<Task> tasks = new ArrayList<>();
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task";
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task:";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done:";
    private static final String TASK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet:";

    private static void validateTaskId(int taskId) throws CrayonIllegalArgumentException {
        if (tasks.isEmpty()) {
            throw new CrayonIllegalArgumentException("No tasks available to perform this action.");
        }

        if (taskId < 1 || taskId >= tasks.size() + 1) {
            throw new CrayonIllegalArgumentException("Invalid TaskID! Please enter a number between 1 - " + (tasks.size() + 1));
        }
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

    /**
     * Add to list of tasks
     * @param taskDescription task to be added to the list
     */
    public static void addTask(Task taskDescription) {
        tasks.add(taskDescription);
        taskAction(taskDescription, TASK_ADDED_MESSAGE);
    }

    public static void deleteTask(int taskId) throws CrayonIllegalArgumentException {

        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        taskAction(task, TASK_REMOVED_MESSAGE);

    }

    public static void markTaskAsDone(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markDone();

        statusAction(task, TASK_DONE_MESSAGE);
    }

    public static void markTaskAsUndone (int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markUndone();

        statusAction(task, TASK_UNDONE_MESSAGE);
    }

    private static void statusAction(Task task, String message) {
        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append(message).append("\n    ");
        sb.append(task).append("\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }

    private static void taskAction(Task task, String message) {
        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append(message).append("\n    ");
        sb.append(task).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }
}
