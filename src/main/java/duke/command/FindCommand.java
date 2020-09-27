package duke.command;

import duke.task.Task;

public class FindCommand extends Command {
    public static final String COMMAND = "find";

    private final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public CommandResult execute() {
        int order = 1;
        String header = "Here are the matching tasks in your list:";
        StringBuilder body = new StringBuilder();
        for (Task task : tasks) {
            if (task.getName().contains(keyword)) {
                body.append(String.format("\t%d. %s%n", order++, task));
            }
        }
        String feedback;
        if (order != 1) {
            feedback = String.format("%s%n%s", header, body.toString());
        } else {
            feedback = String.format("Oh, no! There's no task that matches the keyword: %s%n", keyword);
        }
        return new CommandResult(null, feedback);
    }
}
