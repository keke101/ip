import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int listCount = 0;

    public static void main(String[] args) {
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
        } catch (NumberFormatException nfe) {
            System.out.println("Oh, no! I don't get what you are trying to say!");
            return; //orderStr is not an integer
        }
        int index = order - 1;
        if (index >= 0 && index < listCount) {
            if (tasks[index].isDone()) {
                System.out.println("Mama Mia! You have already done this task~");
            } else {
                tasks[index].markAsDone();
                System.out.println("We did it! Good job little guy.");
            }
            System.out.println("\t" + tasks[index].toString());
        } else {
            System.out.println("Oh, no! There is no such task!");
        }
    }

    /**
     * Prints the entire list of tasks
     */
    private static void printList() {
        int doneCount = 0;
        System.out.println("Here we go! These are the tasks you have:");
        for (int i = 0; i < listCount; i++) {
            Task task = tasks[i];
            int order = i + 1;
            if (task.isDone) {
                doneCount++;
            }
            System.out.println(String.format("\t%d. %s", order, task.toString()));
        }
        System.out.println(String.format("You have %d tasks and you completed %d of them", listCount, doneCount));
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
                tasks[listCount] = createDeadline(args);
                break;
            case TODO:
                tasks[listCount] = createTodo(args);
                break;
            case EVENT:
                tasks[listCount] = createEvent(args);
                break;
            }
            System.out.println("Okey Dokey! Added: " + tasks[listCount++]);
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
