package crayon.ui;

import java.util.List;

import crayon.tasks.Task;

/**
 * This class represents the user interface of the application.
 * It contains messages that the application can display to the user.
 */
public class Ui {

    private static final String WELCOME_MESSAGE =
            "Hello! I'm Crayon, your personal assistant for managing tasks. How can I help you today?";

    private static final String FAREWELL_MESSAGE =
            "Goodbye! It was nice chatting with you. Let me know if you need help with tasks again!\n\n"
            + "The application will close in 3 seconds.";

    private static final String UNKNOWN_COMMAND_MESSAGE = "I'm sorry, but I didn't understand that command.";

    // Task Related Messages
    private static final String TASK_ADDED_MESSAGE = "Got it. I've added this task";
    private static final String TASK_REMOVED_MESSAGE = "Noted. I've removed this task";
    private static final String TASK_DONE_MESSAGE = "Nice! I've marked this task as done";
    private static final String TASK_UNDONE_MESSAGE = "OK, I've marked this task as not done yet";
    private static final String LIST_ALL_TASK_MESSAGE = "Here are the tasks in your list:";
    private static final String LIST_FILTERED_TASKS_MESSAGE = "Here are the matching tasks in your list:";

    /**
     * Returns the welcome message.
     *
     * @return The welcome message.
     */
    public String getWelcomeMessage() {
        return WELCOME_MESSAGE;
    }

    /**
     * Returns the farewell message.
     *
     * @return The farewell message.
     */
    public String getFarewellMessage() {
        return FAREWELL_MESSAGE;
    }

    /**
     * Returns the message to be displayed when an unknown command is entered.
     *
     * @return The message to be displayed.
     */
    public String getUnknownCommandMessage() {
        return UNKNOWN_COMMAND_MESSAGE;
    }

    /**
     * Returns the message to be displayed when a task is added.
     *
     * @param task The task that was added.
     * @param size The size of the task list.
     * @return The message to be displayed.
     */
    public String getTaskAddedMessage(Task task, int size) {
        return formatTaskAction(task, TASK_ADDED_MESSAGE, size);
    }

    /**
     * Returns the message to be displayed when a task is deleted.
     *
     * @param task The task that was deleted.
     * @param size The size of the task list.
     * @return The message to be displayed.
     */
    public String getTaskDeletedMessage(Task task, int size) {
        return formatTaskAction(task, TASK_REMOVED_MESSAGE, size);
    }

    /**
     * Returns the message to be displayed when a task is marked as done.
     *
     * @param task The task that was marked as done.
     * @return The message to be displayed.
     */
    public String getTaskDoneMessage(Task task) {
        return formatStatusAction(task, TASK_DONE_MESSAGE);
    }

    /**
     * Returns the message to be displayed when a task is marked as not done.
     *
     * @param task The task that was marked as not done.
     * @return The message to be displayed.
     */
    public String getTaskUndoneMessage(Task task) {
        return formatStatusAction(task, TASK_UNDONE_MESSAGE);
    }

    /**
     * Returns the message to be displayed when listing all tasks.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The message to be displayed.
     */
    public String getListAllMessage(List<Task> tasks) {
        return formatTaskList(tasks, LIST_ALL_TASK_MESSAGE);
    }

    /**
     * Returns the message to be displayed when listing filtered tasks.
     *
     * @param tasks The list of tasks to be displayed.
     * @return The message to be displayed.
     */
    public String getListFilteredTasksMessage(List<Task> tasks) {
        return formatTaskList(tasks, LIST_FILTERED_TASKS_MESSAGE);
    }

    private String formatTaskList(List<Task> taskList, String header) {
        StringBuilder sb = new StringBuilder(header + "\n");
        int counter = 1;
        for (Task task : taskList) {
            sb.append("    ").append(counter).append(".").append(task).append("\n");
            counter++;
        }
        return sb.toString();
    }

    private String formatStatusAction(Task task, String message) {
        return message + "\n    " + task + "\n";
    }

    private String formatTaskAction(Task task, String message, int size) {
        return message + "\n    " + task + "\n"
                + "Now you have " + size + " tasks in your list\n";
    }
}
