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
}
