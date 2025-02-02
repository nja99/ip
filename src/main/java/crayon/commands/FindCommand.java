package crayon.commands;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;

public class FindCommand extends Command {

    private final String content;

    public FindCommand(String content) {
        super(Action.FIND);
        this.content = content;
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) {
        ui.showMessage(taskList.listFilteredTasks(content));
    }
}
