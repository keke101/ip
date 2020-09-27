package duke.command;

import duke.task.Task;

/**
 * Delete an existing task from the list of tasks
 */
public class DeleteCommand extends Command {
    public static final String COMMAND = "delete";

    private final int index;

    /**
     * Prepare the command for deleting an existing task
     *
     * @param order Order of the task in the list
     */
    public DeleteCommand(int order) {
        index = order - 1;
    }

    /**
     * Delete an existing task from the list of tasks specified by setData()
     *
     * @return If success, CommandResult with the deleted task as retObj and a success message.
     * If fail, CommandResult with failure message
     */
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
