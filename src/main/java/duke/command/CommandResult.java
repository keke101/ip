package duke.command;

public class CommandResult {
    public final Object retObj;
    public final String feedback;
    public final boolean shouldSave;

    public CommandResult(Object retObj, String feedback) {
        this(retObj, feedback, false);
    }

    public CommandResult(Object retObj, String feedback, boolean shouldSave) {
        this.retObj = retObj;
        this.feedback = feedback;
        this.shouldSave = shouldSave;
    }

    @Override
    public String toString() {
        return feedback;
    }
}
