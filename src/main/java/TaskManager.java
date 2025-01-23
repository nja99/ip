import java.util.ArrayList;

public class TaskManager {

    private static ArrayList<String> tasks = new ArrayList<>();

    public static void addTask(String task) {
        tasks.add(task);
    }

    public static void listTasks() {
        int counter = 1;
        for(String task : tasks) {
            System.out.println(counter + ". " + task);
            counter++;
        }
    }
}
