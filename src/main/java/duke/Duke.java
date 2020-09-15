package duke;

import duke.exception.AddDeadlineException;
import duke.exception.AddEventException;
import duke.exception.EmptyNameException;
import duke.task.Deadline;
import duke.task.Event;
import duke.task.Task;
import duke.task.Todo;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.ArrayList;

public class Duke {

    private static ArrayList<Task> tasks = new ArrayList<>();

    public static void main(String[] args) {
        loadFromDisk();
        printWelcomeMsg();
        waitForCommand();
        System.out.println("Bye bye! See you in my gameses~");
    }

    private static void waitForCommand() {
        String input;
        Scanner in = new Scanner(System.in);
        input = in.nextLine();
        while (!input.equals("bye")) {
            String[] temp = input.split(" ", 2);
            String command = temp[0];
            String arguments = temp.length == 2 ? temp[1] : null;
            switch (command) {
            case "list":
                printList();
                break;
            case "done":
                setTaskDone(arguments);
                break;
            case "delete":
                deleteTask(arguments);
                break;
            case "deadline":
                addTask(Task.Type.DEADLINE, arguments);
                break;
            case "todo":
                addTask(Task.Type.TODO, arguments);
                break;
            case "event":
                addTask(Task.Type.EVENT, arguments);
                break;
            default:
                System.out.println("Oh, no! I don't understand you, what are you trying to do?");
                break;
            }
            input = in.nextLine();
        }
    }

    /**
     * Prints the welcome message with a randomly chosen logo
     */
    private static void printWelcomeMsg() {
        String logo = " .----------------.  .----------------.  .----------------.  .----------------.  .----------------. \n" +
                "| .--------------. || .--------------. || .--------------. || .--------------. || .--------------. |\n" +
                "| | ____    ____ | || |      __      | || |  _______     | || |     _____    | || |     ____     | |\n" +
                "| ||_   \\  /   _|| || |     /  \\     | || | |_   __ \\    | || |    |_   _|   | || |   .'    `.   | |\n" +
                "| |  |   \\/   |  | || |    / /\\ \\    | || |   | |__) |   | || |      | |     | || |  /  .--.  \\  | |\n" +
                "| |  | |\\  /| |  | || |   / ____ \\   | || |   |  __ /    | || |      | |     | || |  | |    | |  | |\n" +
                "| | _| |_\\/_| |_ | || | _/ /    \\ \\_ | || |  _| |  \\ \\_  | || |     _| |_    | || |  \\  `--'  /  | |\n" +
                "| ||_____||_____|| || ||____|  |____|| || | |____| |___| | || |    |_____|   | || |   `.____.'   | |\n" +
                "| |              | || |              | || |              | || |              | || |              | |\n" +
                "| '--------------' || '--------------' || '--------------' || '--------------' || '--------------' |\n" +
                " '----------------'  '----------------'  '----------------'  '----------------'  '----------------' \n";
        System.out.println(logo);
        System.out.println("It's-a me, Mario! \nWoohoo! What can I do for you?");
    }

    /**
     * Set the task as done and prints the information
     *
     * @param orderStr Order of the task inside the list
     */
    private static void setTaskDone(String orderStr) {
        int order;
        if (orderStr == null) {
            System.out.println("Oh, no! You didn't specify which task you are done with!");
            return;
        }
        try {
            order = Integer.parseInt(orderStr);
            int index = order - 1;
            Task task = tasks.get(index);
            if (task.isDone()) {
                System.out.println("Mama Mia! You have already done this task~");
            } else {
                task.markAsDone();
                System.out.println("We did it! Good job little guy.");
                saveToDisk();
            }
            System.out.println("\t" + task);
        } catch (NumberFormatException nfe) {
            System.out.println("Oh, no! I don't get what you are trying to say!");
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Oh, no! This task does not exist!");
        }
    }

    /**
     * Prints the entire list of tasks
     */
    private static void printList() {
        int doneCount = 0;
        System.out.println("Here we go! These are the tasks you have:");
        for (int i = 0; i < tasks.size(); i++) {
            Task task = tasks.get(i);
            int order = i + 1;
            if (task.isDone()) {
                doneCount++;
            }
            System.out.printf("\t%d. %s%n", order, task);
        }
        System.out.printf("You have %d tasks and you completed %d of them%n", tasks.size(), doneCount);
    }

    /**
     * Save the current list of tasks to the file "data/duke.txt" on the disk
     */
    private static void saveToDisk() {
        try {
            //Check if directory exists if not create it
            checkDataDir();
            FileWriter fw = new FileWriter("./data/duke.txt");
            for (Task task : tasks) {
                String rawData = task.getRawData() + System.lineSeparator();
                fw.append(rawData);
            }
            fw.close();
        } catch (IOException e) {
            System.out.println("Fail to save data to disk");
            e.printStackTrace();
        }
    }

    /**
     * Load data from a file "data/duke.txt" on the disk
     */
    private static void loadFromDisk() {
        try {
            File f = new File("./data/duke.txt");
            Scanner s = new Scanner(f);
            while (s.hasNext()) {
                String line = s.nextLine();
                Task task = Task.decodeTask(line);
                if (task != null) { //If this task is not invalid
                    tasks.add(task);
                }
            }
        } catch (FileNotFoundException fnfe) {
            System.out.println("Data file not found");
        }
    }

    /**
     * Check if data/ directory exists, if it does not exist, create it
     *
     * @throws IOException Throws if there are any IO exception
     */
    private static void checkDataDir() throws IOException {
        Path path = Paths.get("./data");
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }
    }

    /**
     * Adds new task to the list
     *
     * @param type Type of the new task
     * @param args Arguments that are passed with the type
     */
    public static void addTask(Task.Type type, String args) {
        try {
            switch (type) {
            case DEADLINE:
                tasks.add(createDeadline(args));
                break;
            case TODO:
                tasks.add(createTodo(args));
                break;
            case EVENT:
                tasks.add(createEvent(args));
                break;
            }
            System.out.println("Okey Dokey! Added: " + tasks.get(tasks.size() - 1));
            saveToDisk();
        } catch (AddDeadlineException ade) {
            System.out.println("Oh, no! Deadline's /by cannot be empty!");
        } catch (AddEventException aee) {
            System.out.println("Oh, no! Event's /at cannot be empty!");
        } catch (EmptyNameException ene) {
            System.out.println("Oh, no! Task cannot be created without a name!");
        } catch (NullPointerException npe) {
            System.out.println("Oh, no! More arguments is needed!");
        }
    }

    /**
     * Delete a task from the array list "tasks"
     * @param orderStr Order of the task inside the list
     */
    public static void deleteTask(String orderStr) {
        int order;
        if (orderStr == null) {
            System.out.println("Oh, no! You didn't specify which task you want to delete!");
            return;
        }
        try {
            order = Integer.parseInt(orderStr);
            int index = order - 1;
            Task deletedTask = tasks.remove(index);
            System.out.printf("Alrighty! The following task is gone!%n" +
                    "\t%s%n" +
                    "Now you have %d tasks in the list.%n", deletedTask, tasks.size());
            saveToDisk();
        } catch (NumberFormatException nfe) {
            System.out.println("Oh, no! I don't get what you are trying to say!");
        } catch (IndexOutOfBoundsException ioobe) {
            System.out.println("Oh, no! I can't delete a task that does not exist!");
        }
    }

    /**
     * Creates a new deadline task
     *
     * @param args Arguments of the command
     * @return Newly created deadline
     */
    public static Deadline createDeadline(String args) throws EmptyNameException, AddDeadlineException {
        String[] argArr = args.split(" /by ");
        String name;
        String by;
        name = argArr[0].trim();
        if (name.equals("")) {
            throw new EmptyNameException();
        }
        if (argArr.length != 2) {
            throw new AddDeadlineException();
        }
        by = argArr[1].trim();
        if (by.equals("")) {
            throw new AddDeadlineException();
        }
        return new Deadline(name, by);
    }

    /**
     * Creates a new to-do task
     *
     * @param name Name of the to-do task
     * @return Newly created to-do
     */
    public static Todo createTodo(String name) throws EmptyNameException {
        name = name.trim();
        if (name.equals("")) {
            throw new EmptyNameException();
        }
        return new Todo(name);
    }

    /**
     * Creates a new event task
     *
     * @param args Arguments of the command
     * @return Newly created event
     */
    public static Event createEvent(String args) throws EmptyNameException, AddEventException {
        String[] argArr = args.split(" /at ");
        String name;
        String at;
        name = argArr[0].trim();
        if (name.equals("")) {
            throw new EmptyNameException();
        }
        if (argArr.length != 2) {
            throw new AddEventException();
        }
        at = argArr[1].trim();
        if (at.equals("")) {
            throw new AddEventException();
        }
        return new Event(name, at);
    }
}
