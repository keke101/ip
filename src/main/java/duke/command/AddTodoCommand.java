package duke.command;

import duke.task.Task;
import duke.task.Todo;

/**
 * Create and add a new To-do task into a list of tasks
 */
public class AddTodoCommand extends Command {
    public static final String COMMAND = "todo";

    private final String name;

    /**
     * Prepare the command for creating and adding a new To-do task
     *
     * @param name name of task
     */
    public AddTodoCommand(String name) {
        this.name = name;
    }

    /**
     * Create and add a new to-do task into tasks as specified by setData()
     *
     * @return CommandResult with the new task as retObj and a success message
     */
    @Override
    public CommandResult execute() {
        Task task = new Todo(name);
        tasks.add(task);
        return new CommandResult(task, String.format("Okey Dokey! Added: %s%n", task), true);
    }
}
