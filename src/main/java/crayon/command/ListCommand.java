package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;

public class ListCommand extends Command {
    public ListCommand() {
        super(Action.LIST);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showMessage(taskList.listTasks());
    }
}
