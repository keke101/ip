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

    /**
     * Returns the formatted raw data for saving to hard disk
     *
     * @return String containing the formatted raw data
     */
    @Override
    public String getRawData() {
        String escapedBy = escapePipe(by); //Search for any occurrence of | and escape it with \ 
        return String.format("D | %s | %s", super.getRawData(), escapedBy);
    }

    /**
     * Returns a formatted version of the Deadline for printing
     * E.g. [D] Assignment 1 (by: Thurs, 1pm)
     * 
     * @return formatted version of the Deadline for printing
     */
    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), by);
    }
}
