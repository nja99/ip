package crayon.command;

import crayon.TaskList;
import crayon.Ui;
import crayon.Storage;
import crayon.enums.Action;
import crayon.exceptions.CrayonException;

public abstract class Command {

    protected final Action action;
    protected boolean isExit;

    public Command(Action action) {
        this.action = action;
        this.isExit = false;
    }

    public boolean exitRequested() {
        return this.isExit;
    }

    public abstract void execute(Storage storage, Ui ui, TaskList taskList) throws CrayonException;
}
