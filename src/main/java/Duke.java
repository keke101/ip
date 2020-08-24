import java.util.Scanner;

public class Duke {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String[] list = new String[100];
        int listCount = 0;
        String input;
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
                printList(list, listCount);
            } else {
                list[listCount++] = input;
                System.out.println("Okey Dokey! Added: " + input);
            }
            input = in.nextLine();
        }
        System.out.println("Bye bye! See you in my gameses~");
    }

    public static void printList(String[] list, int listCount) {
        for (int i = 0; i < listCount; i++) {
            System.out.printf("%d. %s%n", i+1, list[i]);
        }
    }
}
