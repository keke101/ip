public class Event extends Task {
    protected String at;

    public Event(String name, String at) {
        this(name, false, at);
    }

    public Event(String name, boolean isDone, String at) {
        super(name, isDone);
        setAt(at);
    }

    /**
     * Gets the "at" property
     *
     * @return "at" value
     */
    public String getAt() {
        return at;
    }

    /**
     * Sets the "at" property
     *
     * @param at new "at" value
     */
    public void setAt(String at) {
        this.at = at.trim();
    }

    @Override
    public String toString() {
        return String.format("[E]%s (at: %s)", super.toString(), at);
    }
}
