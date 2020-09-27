package duke.ui;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

/**
 * Class for handling user interaction such as getting user inputs and printing outputs to user
 */
public class Ui {

    private final Scanner in;
    private final PrintStream out;

    public Ui(InputStream in, PrintStream out) {
        this.in = new Scanner(in);
        this.out = out;
    }

    /**
     * Get command input from the user
     *
     * @return Input from the user
     */
    public String getUserCommand() {
        String line = "";
        while (shouldIgnore(line) && in.hasNextLine()) {
            line = in.nextLine();
        }
        return line;
    }

    /**
     * Prints the welcome message when user starts the program
     */
    public void printWelcomeMessage() {
        String logo = String.format(" .----------------.  .----------------.  .----------------.  .----------------.  .----------------. %n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |%n" +
                "| | ____    ____ | || |      __      | || |  _______     | || |     _____    | || |     ____     | |%n" +
                "| ||_   \\  /   _|| || |     /  \\     | || | |_   __ \\    | || |    |_   _|   | || |   .'    `.   | |%n" +
                "| |  |   \\/   |  | || |    / /\\ \\    | || |   | |__) |   | || |      | |     | || |  /  .--.  \\  | |%n" +
                "| |  | |\\  /| |  | || |   / ____ \\   | || |   |  __ /    | || |      | |     | || |  | |    | |  | |%n" +
                "| | _| |_\\/_| |_ | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |     _| |_    | || |  \\  `--'  /  | |%n" +
                "| ||_____||_____|| || ||____|  |____|| || | |____| |___| | || |    |_____|   | || |   `.____.'   | |%n" +
                "| |              | || |              | || |              | || |              | || |              | |%n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |%n" +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' %n");
        out.printf("%s%nIt's-a me, Mario! %nWoohoo! What can I do for you?%n", logo);
    }

    /**
     * Prints good bye message when user exits the program
     */
    public void printGoodbyeMessage() {
        out.println("Bye bye! See you in my gameses~");
    }

    /**
     * Print feedback to user after executing a command, typically used with CommandResult
     *
     * @param feedback from executing a command
     */
    public void printFeedback(String feedback) {
        if (feedback != null) {
            out.print(feedback);
        }
    }

    /**
     * Check if the command should be ignored
     *
     * @param command from the user
     * @return True if the command is not an empty string after .trim() is executed on it
     */
    private boolean shouldIgnore(String command) {
        return command.trim().isEmpty();
    }
}
