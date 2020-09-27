package duke.command;

import duke.task.Task;
import duke.task.Todo;

public class AddTodoCommand extends Command {
    public static final String COMMAND = "todo";

    private final String name;
    
    public AddTodoCommand(String name) {
        this.name = name;
    }

    @Override
    public CommandResult execute() {
        Task task = new Todo(name);
        tasks.add(task);
        return new CommandResult(task, String.format("Okey Dokey! Added: %s%n", task), true);
    }
}
