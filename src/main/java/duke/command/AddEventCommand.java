package duke.command;

import duke.task.Event;
import duke.task.Task;

/**
 * Create and add a new Event task into a list of tasks
 */
public class AddEventCommand extends Command {
    public static final String COMMAND = "event";
    private final String name;
    private final String at;

    /**
     * Prepare the command for creating and adding a new Event task
     *
     * @param name name of task
     * @param at   Date of the task
     */
    public AddEventCommand(String name, String at) {
        this.name = name;
        this.at = at;
    }

    /**
     * Create and add a new Event task into tasks as specified by setData()
     *
     * @return CommandResult with the new task as retObj and a success message
     */
    @Override
    public CommandResult execute() {
        Task task = new Event(name, at);
        tasks.add(task);
        return new CommandResult(task, String.format("Okey Dokey! Added: %s%n", task), true);
    }
}
