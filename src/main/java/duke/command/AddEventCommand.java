package duke.command;

import duke.task.Event;
import duke.task.Task;

import java.time.LocalDateTime;

public class AddEventCommand extends Command {
    public static final String COMMAND = "event";
    private final String name;
    private final LocalDateTime at;

    public AddEventCommand(String name, LocalDateTime at) {
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
