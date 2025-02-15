package crayon.tasklist;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
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
        assert this.tasks.stream().noneMatch(Objects::isNull) : "Task List cannot contain null items";
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
        Task task = createTaskOfType(taskType, description);
        tasks.add(task);
        return task;
    }

    private Task createTaskOfType(TaskType taskType, String description) throws CrayonTaskCreationException {
        try {
            return switch (taskType) {
                case TODO -> ToDo.createToDoTask(description);
                case DEADLINE -> Deadline.createDeadlineTask(description);
                case EVENT -> Event.createEventTask(description);
            };
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

        assert task.getDoneStatus() : "Task should be marked as done after calling markTaskAsDone";
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

        assert !task.getDoneStatus() : "Task should be marked as undone after calling markTaskAsUndone";
        return task;
    }

    /**
     * Filters the tasks based on the specified pattern.
     *
     * @param pattern The pattern to filter the tasks with.
     * @return The list of tasks that match the pattern.
     */
    public List<Task> filterTasksByPattern(String pattern) {
        List<Task> filteredTask = tasks.stream()
                .filter(task -> task.getDescription().contains(pattern))
                .toList();

        assert filteredTask.stream().allMatch(task -> task.getDescription().contains(pattern))
                : "Filtered Tasks must contain the given pattern";
        return filteredTask;
    }

    /**
     * Filters the tasks based on the specified type.
     *
     * @param taskType The type of task to filter.
     * @return The list of tasks that match the type.
     */
    public List<Task> filterTasksByType(String taskType) {
        List<Task> filteredTask = tasks.stream()
            .filter(task -> task.getType().equals(taskType))
            .toList();

        assert filteredTask.stream().allMatch(task -> task.getType().equals(taskType))
            : "Filtered Tasks must be of the given type";

        return sortTasks(filteredTask, taskType);
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

    private List<Task> sortTasks(List<Task> tasks, String taskType) {
        return switch(taskType.toLowerCase()) {
            case "deadline" -> sortDeadlines(tasks);
            case "event" -> sortEvents(tasks);
            default -> sortTasks(tasks, taskType);
        };
    }

    private List<Task> sortDeadlines(List<Task> deadlines) {
        return deadlines.stream()
            .map(task -> (Deadline) task)
            .sorted(Comparator.comparing(Deadline::getEndDate))
            .collect(Collectors.toList());
    }

    private List<Task> sortEvents(List<Task> events) {
        return events.stream()
            .map(task -> (Event) task)
            .sorted(Comparator.comparing(Event::getStartDate))
            .collect(Collectors.toList());
    }
}
