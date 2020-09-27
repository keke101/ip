package duke.task;

public class Todo extends Task {

    /**
     * Creates a new to-do task with the specified name and isDone = false
     *
     * @param name Name of to-do task
     */
    public Todo(String name) {
        this(name, false);
    }

    /**
     * Creates a new to-do task with the specified name and isDone value
     *
     * @param name   Name of to-do task
     * @param isDone Done status of to-do task
     */
    public Todo(String name, boolean isDone) {
        super(name, isDone);
    }

    /**
     * Returns the formatted raw data for saving to hard disk
     *
     * @return String containing the formatted raw data
     */
    @Override
    public String getRawData() {
        return String.format("T | %s", super.getRawData());
    }
    
    /**
     * Returns a formatted version of the To-do for printing
     * E.g. [T] Buy shower gel
     *
     * @return formatted version of the To-do for printing
     */
    @Override
    public String toString() {
        return String.format("[T]%s ", super.toString());
    }
}
