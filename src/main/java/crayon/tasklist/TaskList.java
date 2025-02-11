package crayon.tasklist;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import crayon.enums.TaskType;
import crayon.exceptions.CrayonIllegalArgumentException;
import crayon.exceptions.CrayonInvalidFormatException;
import crayon.exceptions.CrayonInvalidTaskIdException;
import crayon.exceptions.CrayonTaskCreationException;
import crayon.tasks.Deadline;
import crayon.tasks.Event;
import crayon.tasks.Task;
import crayon.tasks.ToDo;

/**
 * This class represents a list of tasks.
 */
public class TaskList {

    private final List<Task> tasks = new ArrayList<>();
    /**
     * Constructs a new TaskList instance.
     */
    public TaskList() {}

    /**
     * Constructs a new TaskList instance with the specified tasks.
     *
     * @param tasks The tasks to add to the list.
     */
    public TaskList(List<Task> tasks) {
        this.tasks.addAll(tasks);
    }

    /**
     * Creates a new task and adds it to the list.
     *
     * @param taskType The type of task to create.
     * @param description The description of the task.
     * @return The task that was created.
     * @throws CrayonTaskCreationException If the task description is invalid.
     */
    public Task createTask(TaskType taskType, String description) throws CrayonTaskCreationException {
        try {
            Task task = switch (taskType) {
                case TODO -> ToDo.createToDoTask(description);
                case DEADLINE -> Deadline.createDeadlineTask(description);
                case EVENT -> Event.createEventTask(description);
            };
            tasks.add(task);
            return task;
        } catch (CrayonInvalidFormatException e) {
            throw new CrayonTaskCreationException(e.getMessage());
        }
    }

    /**
     * Deletes the task at the specified index.
     *
     * @param taskId The index of the task to delete.
     * @return The task that was deleted.
     * @throws CrayonIllegalArgumentException If the task ID is invalid.
     */
    public Task deleteTask(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        return tasks.remove(taskId - 1);
    }

    /**
     * Marks the task at the specified index as done.
     *
     * @param taskId The index of the task to mark as done.
     * @return The task that was marked as done.
     * @throws CrayonIllegalArgumentException If the task ID is invalid.
     */
    public Task markTaskAsDone(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markDone();

        return task;
    }

    /**
     * Marks the task at the specified index as not done.
     *
     * @param taskId The index of the task to mark as not done.
     * @return The task that was marked as not done.
     * @throws CrayonIllegalArgumentException If the task ID is invalid.
     */
    public Task markTaskAsUndone(int taskId) throws CrayonIllegalArgumentException {
        validateTaskId(taskId);

        Task task = tasks.get(taskId - 1);
        task.markUndone();

        return task;
    }

    /**
     * Filters the tasks based on the specified pattern.
     *
     * @param pattern The pattern to filter the tasks with.
     * @return The list of tasks that match the pattern.
     */
    public List<Task> filterTasks(String pattern) {
        return tasks.stream()
                .filter(task -> task.getDescription().contains(pattern))
                .collect(Collectors.toList());
    }

    /**
     * Returns the list of tasks.
     *
     * @return The list of tasks.
     */
    public List<Task> getTasks() {
        return tasks;
    }

    /**
     * Returns the size of the task list.
     *
     * @return The size of the task list.
     */
    public int getSize() {
        return tasks.size();
    }

    private void validateTaskId(int taskId) throws CrayonIllegalArgumentException {
        if (tasks.isEmpty()) {
            throw new CrayonIllegalArgumentException("No tasks available to perform this action.");
        }

        if (taskId < 1 || taskId >= tasks.size() + 1) {
            throw new CrayonInvalidTaskIdException("Invalid TaskID! Please enter a number between 1 - "
                    + (tasks.size() + 1));
        }
    }
}
