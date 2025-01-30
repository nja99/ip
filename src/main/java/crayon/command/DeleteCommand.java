package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;

public class DeleteCommand extends Command {

    private final String content;

    public DeleteCommand(String content) {
        super(Action.DELETE);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        taskList.deleteTask(Integer.parseInt(content));
    }
}
