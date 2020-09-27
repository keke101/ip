package duke.command;

import duke.task.Task;

/**
 * Mark an existing task as done
 */
public class DoneCommand extends Command {
    public static final String COMMAND = "done";

    private final int index;

    /**
     * Prepare the command for marking an existing task as done
     *
     * @param order Order of the task in the list
     */
    public DoneCommand(int order) {
        index = order - 1;
    }

    /**
     * Mark an existing task in list of tasks specified by setData() as done
     *
     * @return If success, CommandResult with the respective task as retObj and a success message.
     * If task is already done, CommandResult with a neutral message specifying the task is already done.
     * If task does not exist, CommandResult with failure message.
     */
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
