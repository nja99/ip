package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.enums.TaskType;

public class AddToDoCommand extends Command {

    private final String content;

    public AddToDoCommand(String content) {
        super(Action.TODO);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        String todoMessage = taskList.createTask(TaskType.TODO, content);
        ui.showTaskAction(todoMessage);
    }
}
