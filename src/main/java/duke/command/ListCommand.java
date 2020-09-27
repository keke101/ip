package duke.command;

import duke.task.Task;

/**
 * List all tasks from the list
 */
public class ListCommand extends Command {
    public static final String COMMAND = "list";

    /**
     * Prepare the command for listing all the tasks from the list specified by setData()
     *
     * @return CommandResult with the list of tasks in the feedback. retObj will be null
     */
    @Override
    public CommandResult execute() {
        int doneCount = 0;
        int order = 1;
        String header = "Here we go! These are the tasks you have:";
        StringBuilder body = new StringBuilder();

        for (Task task : tasks) {
            if (task.isDone()) {
                doneCount++;
            }
            body.append(String.format("\t%d. %s%n", order++, task));
        }
        String footer = String.format("You have %d tasks and you completed %d of them%n", tasks.size(), doneCount);
        String feedback = String.format("%s%n%s%s", header, body.toString(), footer);
        return new CommandResult(null, feedback);
    }
}
