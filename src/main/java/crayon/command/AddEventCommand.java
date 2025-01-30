package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.enums.TaskType;

public class AddEventCommand extends Command{

    private final String content;

    public AddEventCommand(String content) {
        super(Action.EVENT);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        taskList.createTask(TaskType.EVENT, content);
    }
}
