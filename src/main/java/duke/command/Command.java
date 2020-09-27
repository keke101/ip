package duke.command;

import duke.task.Task;

import java.util.ArrayList;

/**
 * Abstract class for all commands
 */
public abstract class Command {
    protected ArrayList<Task> tasks = null;

    /**
     * Set the data of tasks for the command to be processed
     *
     * @param tasks The list of tasks to be processed
     */
    public void setData(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Method for executing the command. To be overriden by child classes
     *
     * @return Nothing. Throws an UnsupportedOperationException if executed.
     */
    public CommandResult execute() {
        throw new UnsupportedOperationException("This method needs to be implemented by child classes");
    }
}
