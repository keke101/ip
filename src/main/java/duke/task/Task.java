package duke.task;

import duke.exception.DecodeTaskException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

public class Task {
    public static final String INPUT_DATETIME_FORMAT = "yyyy-MM-dd HHmm";
    public static final String OUTPUT_DATETIME_FORMAT = "MMM dd yyyy HH:mm";
    private static final String TICK_SYMBOL = "\u2713";
    private static final String X_SYMBOL = "\u2718";
    protected static final String ESCAPED_PIPE_REPLACE = Matcher.quoteReplacement("\\|");
    protected static final String DELIMITER_PIPE = " \\| ";
    protected static final String ESCAPED_PIPE_REGEX = "\\\\\\|";

    protected String name;
    protected boolean isDone;

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
        return (isDone ? TICK_SYMBOL : X_SYMBOL); //return tick or X symbols
    }

    /**
     * Returns the formatted raw data for saving to hard disk
     *
     * @return String containing the formatted raw data
     */
    public String getRawData() {
        String escapedName = escapePipe(name); //Search for any occurrence of | and escape it with \
        return String.format("%d | %s", isDone ? 1 : 0, escapedName);
    }

    /**
     * Decode the raw data and return the respective object of the task
     *
     * @param rawData Raw data for decoding
     * @return Respective object of the task (e.g. Event, Deadline, To-do)
     */
    public static Task decodeTask(String rawData) {
        String[] fields = decodeRawData(rawData);
        String type = fields[0];
        try {
            switch (type) {
            case "T":
                return decodeTodo(fields);
            case "E":
                return decodeEvent(fields);
            case "D":
                return decodeDeadline(fields);
            default: //Invalid type
                return null;
            }
        } catch (DecodeTaskException dte) {
            return null;
        }
    }

    /**
     * Decode the raw data into array of string for further processing
     * Handles the special character pipe ("|")
     *
     * @param rawData Raw data for decoding
     * @return Array of string with special character processed
     */
    private static String[] decodeRawData(String rawData) {
        String[] splitData = rawData.split(DELIMITER_PIPE, 4);
        for (int i = 0; i < splitData.length; i++) { //Remove any escaped | ('\|')
            splitData[i] = splitData[i].replaceAll(ESCAPED_PIPE_REGEX, "|");
        }
        return splitData;
    }

    /**
     * Decode the To-do task from the decoded raw data
     *
     * @param fields Decoded raw data
     * @return To-do object
     * @throws DecodeTaskException One or more of the field(s) contain invalid data
     */
    private static Todo decodeTodo(String[] fields) throws DecodeTaskException {
        if (fields.length != 3) { //Invalid data
            throw new DecodeTaskException();
        }
        String name = fields[2];
        boolean isDone = decodeIsDone(fields[1]);
        return new Todo(name, isDone);
    }

    /**
     * Decode the Event task from the decoded raw data
     *
     * @param fields Decoded raw data
     * @return Event object
     * @throws DecodeTaskException One or more of the field(s) contain invalid data
     */
    private static Event decodeEvent(String[] fields) throws DecodeTaskException {
        try {
            if (fields.length != 4) { //Invalid data
                throw new DecodeTaskException();
            }
            String name = fields[2];
            boolean isDone = decodeIsDone(fields[1]);
            String atStr = fields[3];
            LocalDateTime at = LocalDateTime.parse(atStr);
            return new Event(name, isDone, at);
        } catch (DateTimeParseException dtpe) {
            throw new DecodeTaskException();
        }
    }

    /**
     * Decode the Deadline task from the decoded raw data
     *
     * @param fields Decoded raw data
     * @return Deadline object
     * @throws DecodeTaskException One or more of the field(s) contain invalid data
     */
    private static Deadline decodeDeadline(String[] fields) throws DecodeTaskException {
        try {
            if (fields.length != 4) { //Invalid data
                throw new DecodeTaskException();
            }
            String name = fields[2];
            boolean isDone = decodeIsDone(fields[1]);
            String byStr = fields[3];
            LocalDateTime by = LocalDateTime.parse(byStr);
            return new Deadline(name, isDone, by);
        } catch (DateTimeParseException dtpe) {
            throw new DecodeTaskException();
        }
    }

    /**
     * Decode the raw form of isDone (either "0" or "1") into boolean
     *
     * @param isDoneStr raw form of isDone ("0" or "1")
     * @return Return the boolean form of isDone
     * @throws DecodeTaskException If raw form is not "0" or "1"
     */
    private static boolean decodeIsDone(String isDoneStr) throws DecodeTaskException {
        if (isDoneStr.equals("0")) {
            return false;
        } else if (isDoneStr.equals("1")) {
            return true;
        } else { //Invalid data, the isDoneStr should only contain either "0" or "1" without any spaces
            throw new DecodeTaskException();
        }
    }

    /**
     * Escape every occurrences of pipe ('|') in the string with \
     *
     * @param str String to escape the pip
     * @return Original string with each occurrence of | replaced with \|
     */
    protected static String escapePipe(String str) {
        return str.replaceAll("\\|", ESCAPED_PIPE_REPLACE);
    }

    /**
     * Parse string as LocalDate
     *
     * @param dateStr date as string format
     */
    public static LocalDateTime parseDateStr(String dateStr) {
        return LocalDateTime.parse(dateStr, DateTimeFormatter.ofPattern(INPUT_DATETIME_FORMAT));
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
