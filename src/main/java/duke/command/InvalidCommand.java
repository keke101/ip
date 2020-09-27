package duke.command;

public class InvalidCommand extends Command {
    private final String errorMessage;

    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    @Override
    public CommandResult execute() {
        return new CommandResult(null, String.format("%s%n", errorMessage));
    }
}
