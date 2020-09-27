package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {

    protected LocalDateTime by;

    /**
     * Creates a new deadline task with specified name, by value and isDone = false
     *
     * @param name Name of deadline task
     * @param by   When the task needs to be done by
     */
    public Deadline(String name, LocalDateTime by) {
        this(name, false, by);
    }

    /**
     * Creates a new deadline task with specified name, by and isDone value
     *
     * @param name Name of deadline task
     * @param by   When the task needs to be done by
     */
    public Deadline(String name, boolean isDone, LocalDateTime by) {
        super(name, isDone);
        setBy(by);
    }

    /**
     * Gets the "by" property
     *
     * @return "by" value
     */
    public LocalDateTime getBy() {
        return by;
    }

    /**
     * Sets the "by" property
     *
     * @param by new "by" value
     */
    public void setBy(LocalDateTime by) {
        this.by = by;
    }

    /**
     * Returns the formatted raw data for saving to hard disk
     *
     * @return String containing the formatted raw data
     */
    @Override
    public String getRawData() {
        return String.format("D | %s | %s", super.getRawData(), by.toString());
    }

    @Override
    public String toString() {
        String dateStr = by.format(DateTimeFormatter.ofPattern(OUTPUT_DATETIME_FORMAT));
        return String.format("[D]%s (by: %s)", super.toString(), dateStr);
    }
}
