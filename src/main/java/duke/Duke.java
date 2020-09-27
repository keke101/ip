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

    public void run() {
        init();
        waitAndRunCommand();
        exitProgram();
    }

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

    private void exitProgram() {
        ui.printGoodbyeMessage();
    }

    private CommandResult executeCommand(Command command) {
        command.setData(tasks);
        CommandResult result = command.execute();
        if (result.shouldSave) {
            storage.save();
        }
        return result;
    }
}
