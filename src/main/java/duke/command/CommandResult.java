package duke.command;

/**
 * Result of a command executed
 */
public class CommandResult {
    public final Object retObj; //Object that is returned as a result of executing a command
    public final String feedback; //Feedback to be printed
    public final boolean shouldSave; //Flag for saving result to disk

    /**
     * Command result with shouldSave set as false by default
     *
     * @param retObj   Object to be returned as a result of executing a command
     * @param feedback Feedback to be printed
     */
    public CommandResult(Object retObj, String feedback) {
        this(retObj, feedback, false);
    }

    /**
     * Command result with specified shouldSave value
     *
     * @param retObj     Object to be returned as a result of executing a command
     * @param feedback   Feedback to be printed
     * @param shouldSave Flag for saving result to disk
     */
    public CommandResult(Object retObj, String feedback, boolean shouldSave) {
        this.retObj = retObj;
        this.feedback = feedback;
        this.shouldSave = shouldSave;
    }

    /**
     * toString() method of CommandResult
     *
     * @return feedback of CommandResult
     */
    @Override
    public String toString() {
        return feedback;
    }
}
