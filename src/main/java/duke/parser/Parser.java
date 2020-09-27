package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.EmptyOrderException;

/**
 * Command parser
 */
public class Parser {

    public static final String CMD_ARG_SEPARATOR = " ";
    public static final int CMD_ARG_SPLIT_LIMIT = 2;
    public static final String ERRMSG_TASK_NOT_EXIST = "Oh, no! This task does not exist!";
    public static final String ERRMSG_INVALID_ORDERNO = "Oh, no! I don't get what you are trying to say!";
    public static final String ERRMSG_NOT_ENOUGH_ARGS = "Oh, no! More arguments is needed!";
    public static final String ERRMSG_NO_NAME = "Oh, no! Task cannot be created without a name!";
    public static final String ERRMSG_EMPTY_D_BY = "Oh, no! Deadline's /by cannot be empty!";
    public static final String ERRMSG_EMPTY_E_AT = "Oh, no! Event's /at cannot be empty!";
    public static final String ERRMSG_UNKNOWN_CMD = "Oh, no! I don't understand you, what are you trying to do?";
    public static final String ERRMSG_DONE_ORDER_EMPTY = "Oh, no! You didn't specify which task you are done with!";
    public static final String ERRMSG_DEL_ORDER_EMPTY = "Oh, no! You didn't specify which task you want to delete!";

    /**
     * Parse the raw input from user and return the respective command to be executed
     *
     * @param input Raw input from user
     * @return A Command object that is associated to the command
     */
    public Command parse(String input) {
        String[] temp = input.split(CMD_ARG_SEPARATOR, CMD_ARG_SPLIT_LIMIT);
        String command = temp[0];
        String rawArgs = temp.length == 2 ? temp[1] : null;
        switch (command) {
        case ListCommand.COMMAND:
            return prepareList();
        case DoneCommand.COMMAND:
            return prepareDone(rawArgs);
        case DeleteCommand.COMMAND:
            return prepareDelete(rawArgs);
        case AddTodoCommand.COMMAND:
            return prepareAddTodo(rawArgs);
        case AddDeadlineCommand.COMMAND:
            return prepareAddDeadline(rawArgs);
        case AddEventCommand.COMMAND:
            return prepareAddEvent(rawArgs);
        case ExitCommand.COMMAND:
            return new ExitCommand();
        default:
            return new InvalidCommand(ERRMSG_UNKNOWN_CMD);
        }
    }

    /**
     * List all tasks from the list
     *
     * @return ListCommand object
     */
    public Command prepareList() {
        return new ListCommand();
    }

    /**
     * Mark an existing task as done
     *
     * @return DoneCommand object
     */
    public Command prepareDone(String args) {
        int order;
        try {
            order = parseArgsAsOrder(args);
            return new DoneCommand(order);
        } catch (EmptyOrderException eoe) {
            return new InvalidCommand(ERRMSG_DONE_ORDER_EMPTY);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(ERRMSG_INVALID_ORDERNO);
        } catch (IndexOutOfBoundsException ioobe) {
            return new InvalidCommand(ERRMSG_TASK_NOT_EXIST);
        }
    }

    /**
     * Delete an existing task from the list of tasks
     *
     * @param args Raw form of the argument given by user
     * @return DeleteCommand object
     */
    public Command prepareDelete(String args) {
        int order;
        try {
            order = parseArgsAsOrder(args);
            return new DeleteCommand(order);
        } catch (EmptyOrderException eoe) {
            return new InvalidCommand(ERRMSG_DEL_ORDER_EMPTY);
        } catch (NumberFormatException nfe) {
            return new InvalidCommand(ERRMSG_INVALID_ORDERNO);
        } catch (IndexOutOfBoundsException ioobe) {
            return new InvalidCommand(ERRMSG_TASK_NOT_EXIST);
        }
    }

    /**
     * Create and add a new To-do task into a list of tasks
     *
     * @param args Raw form of the argument given by user
     * @return AddTodoCommand object
     */
    public Command prepareAddTodo(String args) {
        if (args == null) {
            return new InvalidCommand(ERRMSG_NOT_ENOUGH_ARGS);
        }
        String name = args.trim();
        if (name.equals("")) {
            return new InvalidCommand(ERRMSG_NO_NAME);
        }
        return new AddTodoCommand(name);
    }

    /**
     * Create and add a new Deadline task into a list of tasks
     *
     * @param args Raw form of the argument given by user
     * @return AddDeadlineCommand object
     */
    public Command prepareAddDeadline(String args) {
        if (args == null) {
            return new InvalidCommand(ERRMSG_NOT_ENOUGH_ARGS);
        }
        String[] argArr = args.split(" /by ");
        String name;
        String by;
        name = argArr[0].trim();
        if (name.equals("")) {
            return new InvalidCommand(ERRMSG_NO_NAME);
        }
        if (argArr.length != 2) {
            return new InvalidCommand(ERRMSG_EMPTY_D_BY);
        }
        by = argArr[1].trim();
        if (by.equals("")) {
            return new InvalidCommand(ERRMSG_EMPTY_D_BY);
        }
        return new AddDeadlineCommand(name, by);
    }

    /**
     * Create and add a new Event task into a list of tasks
     *
     * @param args Raw form of the argument given by user
     * @return AddEventCommand object
     */
    public Command prepareAddEvent(String args) {
        if (args == null) {
            return new InvalidCommand(ERRMSG_NOT_ENOUGH_ARGS);
        }
        String[] argArr = args.split(" /at ");
        String name;
        String at;
        name = argArr[0].trim();
        if (name.equals("")) {
            return new InvalidCommand(ERRMSG_NO_NAME);
        }
        if (argArr.length != 2) {
            return new InvalidCommand(ERRMSG_EMPTY_E_AT);
        }
        at = argArr[1].trim();
        if (at.equals("")) {
            return new InvalidCommand(ERRMSG_EMPTY_E_AT);
        }
        return new AddEventCommand(name, at);
    }

    /**
     * Parse the argument given as order (a positive integer)
     *
     * @param arg Raw argument
     * @return Order (a positive integer)
     * @throws EmptyOrderException If arg is null
     */
    private int parseArgsAsOrder(String arg) throws EmptyOrderException {
        if (arg == null) {
            throw new EmptyOrderException();
        }
        return Integer.parseInt(arg);
    }
}
