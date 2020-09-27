package duke.command;

public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    @Override
    public CommandResult execute() {
        return new CommandResult(null, null, true);
    }
}
