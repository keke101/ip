package duke.parser;

import duke.command.AddDeadlineCommand;
import duke.command.AddEventCommand;
import duke.command.AddTodoCommand;
import duke.command.Command;
import duke.command.DeleteCommand;
import duke.command.DoneCommand;
import duke.command.ExitCommand;
import duke.command.FindCommand;
import duke.command.InvalidCommand;
import duke.command.ListCommand;
import duke.exception.EmptyOrderException;

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
    public static final String ERRMSG_KEYWORD_EMPTY = "Oh, no! You have to specify what to find!";

    public Command parse(String input) {
        String[] temp = input.split(CMD_ARG_SEPARATOR, CMD_ARG_SPLIT_LIMIT);
        String command = temp[0];
        String rawArgs = temp.length == 2 ? temp[1] : null;
        switch (command) {
        case FindCommand.COMMAND:
            return prepareFind(rawArgs);
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

    public Command prepareFind(String args) {
        try {
            String keyword = args.trim();
            if (keyword.equals("")) {
                return new InvalidCommand(ERRMSG_KEYWORD_EMPTY);
            }
            return new FindCommand(keyword);
        } catch (NullPointerException npe) {
            return new InvalidCommand(ERRMSG_KEYWORD_EMPTY);
        }
    }

    public Command prepareList() {
        return new ListCommand();
    }

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

    private int parseArgsAsOrder(String arg) throws EmptyOrderException {
        if (arg == null) {
            throw new EmptyOrderException();
        }
        return Integer.parseInt(arg);
    }
}
