package duke.command;

import duke.task.Deadline;
import duke.task.Task;

import java.time.LocalDateTime;

public class AddDeadlineCommand extends Command {
    public static final String COMMAND = "deadline";

    private final String name;
    private final LocalDateTime by;

    public AddDeadlineCommand(String name, LocalDateTime by) {
        this.name = name;
        this.by = by;
    }

    @Override
    public CommandResult execute() {
        Task task = new Deadline(name, by);
        tasks.add(task);
        return new CommandResult(task, String.format("Okey Dokey! Added: %s%n", task), true);
    }
}
