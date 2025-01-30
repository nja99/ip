package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;
public class UnmarkCommand extends Command {

    private final String content;

    public UnmarkCommand(String content) {
        super(Action.UNMARK);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        String unmarkMessage = taskList.markTaskAsUndone(Integer.parseInt(content));
        ui.showStatusAction(unmarkMessage);
    }
}
