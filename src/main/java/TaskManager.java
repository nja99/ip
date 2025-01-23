import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Add to list of tasks
     * @param taskDescription task to be added to the list
     */
    public static void addTask(String taskDescription) {
        tasks.add(new Task(taskDescription));
    }

    /**
     * Lists all tasks
     */
    public static void listTasks() {
        int counter = 1;

        StringBuilder sb = new StringBuilder(Constants.SEPARATOR);
        sb.append("Here are the tasks in your list:\n");
        for(Task task : tasks) {
            sb.append(counter).append(".").append(task);
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
        System.out.println("Nice! I've marked this task as done:\n");
        System.out.println(task);
    }

    public static void markTaskAsUndone(int taskId) {
        if(taskId < 0 || taskId >= tasks.size()) {
            System.out.println("Invalid task id: " + taskId);
            return;
        }

        Task task = tasks.get(taskId - 1);
        task.markUndone();
        System.out.println("OK, I've marked this task as not done yet:\n");
        System.out.println(task);
    }
}
