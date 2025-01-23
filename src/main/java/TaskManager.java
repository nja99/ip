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
        sb.append("Here are the tasks in your list:\n    ");
        for(Task task : tasks) {
            sb.append(counter).append(".").append(task).append("\n");
            counter++;
        }
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }

    public static void markTaskAsDone(int taskId) {
        if(taskId < 1 || taskId >= tasks.size() + 1) {
            System.out.println("Invalid task id: " + taskId);
            return;
        }

        Task task = tasks.get(taskId - 1);
        task.markDone();

        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append("Nice! I've marked this task as done:\n    ");
        sb.append(task).append("\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }

    public static void markTaskAsUndone(int taskId) {
        if(taskId < 1 || taskId >= tasks.size() + 1) {
            System.out.println("Invalid task id: " + taskId);
            return;
        }

        Task task = tasks.get(taskId - 1);
        task.markUndone();

        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append("OK, I've marked this task as not done yet:\n    ");
        sb.append(task).append("\n");
        sb.append(Constants.SEPARATOR);
        System.out.println(sb);
    }
}
