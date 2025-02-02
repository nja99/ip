package crayon;

import crayon.enums.TaskType;
import crayon.exceptions.CrayonInvalidTaskIdException;
import crayon.exceptions.CrayonIllegalArgumentException;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.tasks.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TaskList {

    private final List<Task> tasks = new ArrayList<>();

    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task";
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done";
    private static final String TASK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet";

    public TaskList() {}

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

    public List<Task> filterTasks(String pattern) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(pattern))
                .collect(Collectors.toList());
    }

    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Lists all tasks
     */
    public String listAllTasks() {
        return formatTaskList(tasks, "Here are the tasks in your list:");
    }

    public String listFilteredTasks(String pattern) {
        List<Task> filteredTasks = filterTasks(pattern);
        return formatTaskList(filteredTasks, "Here are the matching tasks in your list:");
    }

    public String deleteTask(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        tasks.remove(taskId - 1);
        return formatTaskAction(task, TASK_REMOVED_MESSAGE);

    }

    public String markTaskAsDone(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markDone();

        return formatStatusAction(task, TASK_DONE_MESSAGE);
    }

    public String markTaskAsUndone (int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markUndone();

        return formatStatusAction(task, TASK_UNDONE_MESSAGE);
    }

    private String formatTaskList(List<Task> taskToList, String header) {
        StringBuilder sb = new StringBuilder(header + "\n");
        int counter = 1;
        for (Task task : taskToList) {
            sb.append("    ").append(counter).append(".").append(task).append("\n");
            counter++;
        }
        return sb.toString();
    }

    private String formatStatusAction(Task task, String message) {
        return message + "\n    " + task + "\n";
    }

    private String formatTaskAction(Task task, String message) {
        return message + "\n    " + task + "\n"
                + "Now you have " + tasks.size() + " tasks in your list\n";
    }
}
