package duke.command;

import duke.task.Task;

public class DoneCommand extends Command {
    public static final String COMMAND = "done";

    private final int index;

    public DoneCommand(int order) {
        index = order - 1;
    }

    @Override
    public CommandResult execute() {
        try {
            Task task = tasks.get(index);
            if (task.isDone()) {
                return new CommandResult(task, String.format("Mama Mia! You have already done this task~%n\t%s%n", task));
            }
            task.markAsDone();
            return new CommandResult(task, String.format("We did it! Good job little guy.%n\t%s%n", task), true);
        } catch (IndexOutOfBoundsException ioobe) {
            return new CommandResult(null, String.format("Oh, no! This task does not exist!%n"));
        }
    }
}
