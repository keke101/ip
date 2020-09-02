public class Task {
    private static final String TICK_SYMBOL = "\u2713";
    private static final String X_SYMBOL = "\u2718";
    protected String name;
    protected boolean isDone;

    public enum Type {
        DEADLINE, EVENT, TODO
    }

    /**
     * Create a new task with provided name and isDone as false
     *
     * @param name Name of the task
     */
    public Task(String name) {
        this(name, false);
    }

    /**
     * Create a new task with provided name and isDone value
     *
     * @param name   Name of task
     * @param isDone Done status of the task
     */
    public Task(String name, boolean isDone) {
        setName(name);
        setDone(isDone);
    }

    /**
     * Returns the name of the task
     *
     * @return Name of the task
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the task
     *
     * @param name Name of the Task
     */
    public void setName(String name) {
        this.name = name.trim();
    }

    /**
     * Return done status of the task
     *
     * @return isDone value
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Set the isDone boolean of the task
     *
     * @param done New done status of task
     */
    public void setDone(boolean done) {
        isDone = done;
    }

    /**
     * Mark the task as done
     */
    public void markAsDone() {
        setDone(true);
    }

    /**
     * Get the status icon depending on isDone
     *
     * @return Tick if isDone == true, cross if isDone == false
     */
    public String getStatusIcon() {
        return (isDone ? TICK_SYMBOL : X_SYMBOL ); //return tick or X symbols
    }

    /**
     * Returns a formatted string of the task
     *
     * @return [x] Name
     */
    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getName());
    }
}
