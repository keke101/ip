package duke;

import duke.command.Command;
import duke.command.CommandResult;
import duke.command.ExitCommand;
import duke.parser.Parser;
import duke.storage.Storage;
import duke.task.TaskList;
import duke.ui.Ui;

import java.io.IOException;

import static java.lang.System.exit;

public class Duke {
    private static final String SAVE_FILE = "./data/duke.txt";

    private TaskList tasks;
    private Ui ui;
    private Parser parser;
    private Storage storage;

    public static void main(String[] args) {
        new Duke().run();
    }

    /**
     * Run the program
     */
    public void run() {
        init();
        waitAndRunCommand();
        exitProgram();
    }

    /**
     * Initialise the program
     * Exits with status 1 if the program fails to create the parent directory for data file
     */
    private void init() {
        try {
            tasks = new TaskList();
            ui = new Ui(System.in, System.out);
            parser = new Parser();
            storage = new Storage(SAVE_FILE, tasks);
            storage.load();
        } catch (IOException e) {
            exit(1); //exit with error number
        }
    }

    /**
     * Wait for user input and run the user command upon input
     * Exits when user types "bye"
     */
    private void waitAndRunCommand() {
        ui.printWelcomeMessage();
        Command command;
        do {
            String commandTxt = ui.getUserCommand();
            command = parser.parse(commandTxt);
            CommandResult result = executeCommand(command);
            ui.printFeedback(result.toString());
        } while (!(command instanceof ExitCommand));
    }

    /**
     * Executes the following when program is exiting:
     * 1. Prints good bye message
     */
    private void exitProgram() {
        ui.printGoodbyeMessage();
    }

    /**
     * Executes the command and return the result
     *
     * @param command CommandObject that specify the command to execute
     * @return CommandResult object that contains the result of the command
     */
    private CommandResult executeCommand(Command command) {
        command.setData(tasks);
        CommandResult result = command.execute();
        if (result.shouldSave) {
            storage.save();
        }
        return result;
    }
}
