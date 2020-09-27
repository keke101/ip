package duke.command;

/**
 * Exit program
 */
public class ExitCommand extends Command {
    public static final String COMMAND = "bye";

    /**
     * Used to exit the program. retObj and feedback is null. shouldSave will be set as true.
     *
     * @return A CommandResult with shouldSave = true
     */
    @Override
    public CommandResult execute() {
        return new CommandResult(null, null, true);
    }
}
