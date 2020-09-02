import java.util.Scanner;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int listCount = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        printWelcomeMsg();
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
        System.out.println("Bye bye! See you in my gameses~");
    }

    /**
     * Prints the welcome message with a randomly chosen logo
     */
    public static void printWelcomeMsg() {
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
     * Adds new task to the list
     *
     * @param type Type of the new task
     * @param args Arguments that are passed with the type
     */
    public static void addTask(Task.Type type, String args) {
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
        if (tasks[listCount] != null) {
            System.out.println("Okey Dokey! Added: " + tasks[listCount++]);
        } else {
            System.out.println("Oh, no! A valid input please~");
        }
    }

    /**
     * Creates a new deadline task
     *
     * @param args Arguments of the command
     * @return Newly created deadline
     */
    public static Deadline createDeadline(String args) {
        if (args == null) {
            return null;
        }
        String[] argArr = args.split("/by");
        if (argArr.length != 2) {
            return null;
        }
        String name = argArr[0];
        String by = argArr[1];
        return new Deadline(name, by);
    }

    /**
     * Creates a new to-do task
     *
     * @param name Name of the to-do task
     * @return Newly created to-do
     */
    public static Todo createTodo(String name) {
        return new Todo(name);
    }

    /**
     * Creates a new event task
     *
     * @param args Arguments of the command
     * @return Newly created event
     */
    public static Event createEvent(String args) {
        if (args == null) {
            return null;
        }
        String[] argArr = args.split("/at");
        if (argArr.length != 2) {
            return null;
        }
        String name = argArr[0];
        String at = argArr[1];
        return new Event(name, at);
    }

    /**
     * Set the task as done and prints the information
     *
     * @param orderStr Order of the task inside the list
     */
    public static void setTaskDone(String orderStr) {
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
    public static void printList() {
        int doneCount = 0;
        System.out.println("Here we go! These are the tasks you have:");
        for (int i = 0; i < listCount; i++) {
            Task task = tasks[i];
            if (task.isDone) {
                doneCount++;
            }
            System.out.println(String.format("\t%d. %s", (i + 1), task.toString()));
        }
        System.out.println(String.format("You have %d tasks and you completed %d of them", listCount, doneCount));
    }
}
