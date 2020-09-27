package duke.command;

import duke.task.Task;

import java.util.ArrayList;

public abstract class Command {
    protected ArrayList<Task> tasks = null;

    public void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public CommandResult execute() {
        throw new UnsupportedOperationException("This method needs to be implemented by child classes");
    }
}
