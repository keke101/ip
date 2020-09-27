package duke.command;

import duke.task.Event;
import duke.task.Task;

public class AddEventCommand extends Command {
    public static final String COMMAND = "event";
    private final String name;
    private final String at;

    public AddEventCommand(String name, String at) {
        this.name = name;
        this.at = at;
    }

    @Override
    public CommandResult execute() {
        Task task = new Event(name, at);
        tasks.add(task);
        return new CommandResult(task, String.format("Okey Dokey! Added: %s%n", task), true);
    }
}
