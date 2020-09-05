package duke.task;

public class Deadline extends Task {
    protected String by;

    /**
     * Creates a new deadline task with specified name, by value and isDone = false
     *
     * @param name Name of deadline task
     * @param by   When the task needs to be done by
     */
    public Deadline(String name, String by) {
        this(name, false, by);
    }

    /**
     * Creates a new deadline task with specified name, by and isDone value
     *
     * @param name Name of deadline task
     * @param by   When the task needs to be done by
     */
    public Deadline(String name, boolean isDone, String by) {
        super(name, isDone);
        setBy(by);
    }

    /**
     * Gets the "by" property
     *
     * @return "by" value
     */
    public String getBy() {
        return by;
    }

    /**
     * Sets the "by" property
     *
     * @param by new "by" value
     */
    public void setBy(String by) {
        this.by = by.trim();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
