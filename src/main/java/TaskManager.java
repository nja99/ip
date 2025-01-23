import exceptions.CrayonIllegalArgumentException;
import tasks.Task;

import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Add to list of tasks
     * @param taskDescription task to be added to the list
     */
    public static void addTask(Task taskDescription) {
        tasks.add(taskDescription);

        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append("Got it. I've added this task:\n    ");
        sb.append(taskDescription).append("\n");
        sb.append("Now you have ").append(tasks.size()).append(" tasks in the list\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
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

    public static void markTaskAsDone(int taskId) throws CrayonIllegalArgumentException {
        if(taskId < 1 || taskId >= tasks.size() + 1) {
            throw new CrayonIllegalArgumentException("Invalid TaskID! Please a number between 1 - " + (tasks.size() + 1));
        }

        Task task = tasks.get(taskId - 1);
        task.markDone();
        printAction(task, "Nice! I've marked this task as done:");
    }

    public static void markTaskAsUndone (int taskId) throws CrayonIllegalArgumentException {
        if(taskId < 1 || taskId >= tasks.size() + 1) {
            throw new CrayonIllegalArgumentException("Invalid TaskID! Please a number between 1 - " + (tasks.size() + 1));
        }

        Task task = tasks.get(taskId - 1);
        task.markUndone();
        printAction(task, "OK, I've marked this task as not done yet:");
    }

    private static void printAction(Task task, String message) {
        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append(message).append("\n    ");
        sb.append(task).append("\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }
}
