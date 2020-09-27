package duke.command;

import duke.task.Task;

public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    private final int index;

    public DeleteCommand(int order) {
        index = order - 1;
    }

    @Override
    public CommandResult execute() {
        try {
            Task task = tasks.remove(index);
            String feedback = String.format("Alrighty! The following task is gone!%n" +
                    "\t%s%n" +
                    "Now you have %d tasks in the list.%n", task, tasks.size());
            return new CommandResult(task, feedback, true);
        } catch (IndexOutOfBoundsException ioobe) {
            return new CommandResult(null, String.format("Oh, no! I can't delete a task that does not exist!%n"));
        }
    }
}
