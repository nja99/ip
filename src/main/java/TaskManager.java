import java.util.ArrayList;

public class TaskManager {

    private static final ArrayList<String> tasks = new ArrayList<>();

    /**
     * Add to list of tasks
     * @param task task to be added to the list
     */
    public static void addTask(String task) {
        tasks.add(task);
    }

    /**
     * Lists all tasks
     */
    public static void listTasks() {
        int counter = 1;
        System.out.println(Constants.SEPARATOR);
        for(String task : tasks) {
            System.out.println(counter + ". " + task);
            counter++;
        }
        System.out.println(Constants.SEPARATOR);
    }
}
