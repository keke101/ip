package duke.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    protected LocalDateTime at;

    public Event(String name, LocalDateTime at) {
        this(name, false, at);
    }

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

    @Override
    public String toString() {
        String dateStr = at.format(DateTimeFormatter.ofPattern(OUTPUT_DATETIME_FORMAT));
        return String.format("[E]%s (at: %s)", super.toString(), dateStr);
    }
}
