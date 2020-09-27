package duke.command;

import duke.task.Deadline;
import duke.task.Task;

/**
 * Create and add a new Deadline task into a list of tasks
 */
public class AddDeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    private final String name;
    private final String by;

    /**
     * Prepare the command for creating and adding a new Deadline task
     *
     * @param name name of task
     * @param by   deadline of the task
     */
    public AddDeadlineCommand(String name, String by) {
        this.name = name;
        this.by = by;
    }

    /**
     * Create and add a new Deadline task into tasks as specified by setData()
     *
     * @return CommandResult with the new task as retObj and a success message
     */
    @Override
    public CommandResult execute() {
        Task task = new Deadline(name, by);
        tasks.add(task);
        return new CommandResult(task, String.format("Okey Dokey! Added: %s%n", task), true);
    }
}
