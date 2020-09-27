package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Allows users to create event tasks
 */
public class Event extends Task {
    protected LocalDateTime at;

    /**
     * Creates a new event task with specified name, at value and isDone = false
     * 
     * @param name Name of event task
     * @param at When the event happens
     */
    public Event(String name, LocalDateTime at) {
        this(name, false, at);
    }

    /**
     * Creates a new event task with specified name, at and isDone value
     *
     * @param name Name of event task
     * @param isDone Done status of task
     * @param at When the event happens
     */
    public Event(String name, boolean isDone, LocalDateTime at) {
        super(name, isDone);
        setAt(at);
    }

    /**
     * Gets the "at" property
     *
     * @return "at" value
     */
    public LocalDateTime getAt() {
        return at;
    }

    /**
     * Sets the "at" property
     *
     * @param at new "at" value
     */
    public void setAt(LocalDateTime at) {
        this.at = at;
    }

    /**
     * Returns the formatted raw data for saving to hard disk
     *
     * @return String containing the formatted raw data
     */
    @Override
    public String getRawData() {
        return String.format("E | %s | %s", super.getRawData(), at.toString());
    }

    /**
     * Returns a formatted version of the Event for printing
     * E.g. [E] Birthday (at: Thurs, 1pm)
     *
     * @return formatted version of the Event for printing
     */
    @Override
    public String toString() {
        String dateStr = at.format(DateTimeFormatter.ofPattern(OUTPUT_DATETIME_FORMAT));
        return String.format("[E]%s (at: %s)", super.toString(), dateStr);
    }
}
