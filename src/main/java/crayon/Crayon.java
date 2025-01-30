package crayon;

import crayon.enums.Action;
import crayon.enums.TaskType;
import crayon.exceptions.CrayonUnsupportedTaskException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Crayon {

    private final Ui ui;
    private final Storage storage;
    private TaskList taskList;

    public Crayon() {
        ui = new Ui();
        storage = new Storage();

        try {
            taskList = new TaskList(storage.loadTasksFromCSV());
        } catch (IOException e) {
            ui.showErrorMessage("Error loading tasks: " + e.getMessage());
            taskList = new TaskList();
        }
    }

    public static void main(String[] args) {
        new Crayon().run();
    }

    public void run() {
        ui.showWelcome();
        boolean isExit = false;
//        while(!isExit) {
//            try {
//                String userCommand = ui.readUserCommand();
//            } catch (IOException e) {
//                ui.showErrorMessage("Error reading user crayon.command: " + e.getMessage());
//            }
//        }

        try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {
            while (!isExit) {
                String userInput = br.readLine();
                String[] args = userInput.split(" ", 2);
                String taskDescription = "";

                if (args.length > 1) taskDescription = args[1].trim();

                try {
                    Action action = Action.fromString(args[0]);

                    switch (action) {
                        case LIST:
                            taskList.listTasks();
                            break;
                        case TODO:
                        case DEADLINE:
                        case EVENT:
                            TaskType taskType = TaskType.fromString(args[0]);
                            taskList.createTask(taskType, taskDescription);
                            break;
                        case DELETE:
                            taskList.deleteTask(Integer.parseInt(args[1]));
                            break;
                        case MARK:
                            taskList.markTaskAsDone(Integer.parseInt(args[1]));
                            break;
                        case UNMARK:
                            taskList.markTaskAsUndone(Integer.parseInt(args[1]));
                            break;
                        case BYE:
                            isExit = true;
                            ui.showFarewell();
                            storage.saveTasksToCSV(taskList.getTasks());
                            break;
                        default:
                            throw new CrayonUnsupportedTaskException();
                    }
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}