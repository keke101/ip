public class Task {
    private String name;
    private boolean isDone;

    public Task(String name) {
        this(name, false);
    }
    public Task(String name, boolean isDone) {
        setName(name);
        setDone(isDone);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return isDone;
    }

    public void setDone(boolean done) {
        isDone = done;
    }

    public void markAsDone() {
        setDone(true);
    }

    public String getStatusIcon() {
        return (isDone ? "\u2713" : "\u2718"); //return tick or X symbols
    }

    public String toString() {
        return String.format("[%s] %s", getStatusIcon(), getName());
    }
}
