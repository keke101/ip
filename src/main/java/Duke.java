import java.util.Scanner;
import java.util.regex.Pattern;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String input;
        Task[] tasks = new Task[100];
        int listCount = 0;
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
            if ("list".equals(input)) {
                printList(tasks, listCount);
            } else if (pattern.matcher(input).find()) {
                String strOrder = input.substring(input.indexOf(' ') + 1);
                int order = Integer.parseInt(strOrder);
                setTaskDone(tasks, order, listCount);
            } else {
                listCount = addTasks(tasks, input, listCount);
            }
            input = in.nextLine();
        }
        System.out.println("Bye bye! See you in my gameses~");
    }

    public static int addTasks(Task[] tasks, String name, int size) {
        tasks[size++] = new Task(name);
        System.out.println("Okey Dokey! Added: " + name);
        return size;
    }

    public static void setTaskDone(Task[] tasks, int order, int size) {
        int index = order - 1;
        if (index >= 0 && index < size) {
            tasks[index].markAsDone();
            System.out.println("Wa-hoo! The following task is done:"
                    + System.lineSeparator() + "\t" + tasks[index].toString());
        } else {
            System.out.println("Oh no! There is no such task!");
        }
    }

    public static void printList(Task[] tasks, int listCount) {
        for (int i = 0; i < listCount; i++) {
            Task task = tasks[i];
            System.out.println(Integer.toString(i + 1) + "." + task.toString());
        }
    }
}
