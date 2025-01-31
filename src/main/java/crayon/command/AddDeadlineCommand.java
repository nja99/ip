package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.enums.TaskType;

public class AddDeadlineCommand extends Command {

    private final String content;

    public AddDeadlineCommand(String content) {
        super(Action.DEADLINE);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        String deadlineMessage = taskList.createTask(TaskType.DEADLINE, content);
        ui.showTaskAction(deadlineMessage);
    }
}
