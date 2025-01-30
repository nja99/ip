package crayon.command;

import crayon.Storage;
import crayon.TaskList;
import crayon.Ui;
import crayon.enums.Action;
import crayon.exceptions.CrayonIllegalArgumentException;

import java.io.IOException;

public class ByeCommand extends Command {

    public ByeCommand() {
        super(Action.BYE);
    }

    @Override
    public void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonIllegalArgumentException {
        this.isExit = true;
        try {
            storage.saveTasksToCSV(taskList.getTasks());
            ui.showFarewell();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
