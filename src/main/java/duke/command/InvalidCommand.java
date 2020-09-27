package duke.command;

/**
 * Used when command is invalid
 */
public class InvalidCommand extends Command {
    private final String errorMessage;

    /**
     * Prepare the command with an error message to be printed
     *
     * @param errorMessage Error message that states the reason of invalid command
     */
    public InvalidCommand(String errorMessage) {
        this.errorMessage = errorMessage;
    }

    /**
     * Specify the error message that will be printed
     *
     * @return CommandResult with error message to be printed specified
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(null, String.format("%s%n", errorMessage));
    }
}
