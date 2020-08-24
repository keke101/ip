import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    private static Task[] tasks = new Task[100];
    private static int listCount = 0;

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        Pattern pattern = Pattern.compile("^done \\d$");
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
        input = in.nextLine();
        while (!input.equals("bye")) {
            if ("list".equals(input)) { //check if it is a list command
                printList();
            } else if (pattern.matcher(input).find()) { //check if it is a done command (e.g. done <positive integer>
                String strOrder = input.substring(input.indexOf(' ') + 1);
                int order = Integer.parseInt(strOrder);
                setTaskDone(order);
            } else { //if not, add as task
                addTasks(input);
            }
            input = in.nextLine();
        }
        System.out.println("Bye bye! See you in my gameses~");
    }

    public static void addTasks(String name) {
        tasks[listCount++] = new Task(name);
        System.out.println("Okey Dokey! Added: " + name);
    }

    public static void setTaskDone(int order) {
        int index = order - 1;
        if (index >= 0 && index < listCount) {
            tasks[index].markAsDone();
            System.out.println("Wa-hoo! The following task is done:"
                    + System.lineSeparator() + "\t" + tasks[index].toString());
        } else {
            System.out.println("Oh no! There is no such task!");
        }
    }


    public static void printList() {
        System.out.println("Here we go! The list of tasks:");
        for (int i = 0; i < listCount; i++) {
            Task task = tasks[i];
            System.out.println(Integer.toString(i + 1) + "." + task.toString());
        }
    }
}
