package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;
public class MarkCommand extends Command {

    private final String content;

    public MarkCommand(String content) {
        super(Action.MARK);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        taskList.markTaskAsDone(Integer.parseInt(content));
    }
}
