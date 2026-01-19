/**
 * A personal assistant chatbot
 *
 * @author JustATin555
 */

import java.util.Scanner;

import storage.Tasks;

public class ForgetfulDave {
    // 3D-ASCII Art generated with https://patorjk.com/software/taag/
    private static final String DAVE_LOGO = """
             ________  ________  ___      ___ _______
            |\\   ___ \\|\\   __  \\|\\  \\    /  /|\\  ___ \\
            \\ \\  \\_|\\ \\ \\  \\|\\  \\ \\  \\  /  / | \\   __/|
             \\ \\  \\ \\\\ \\ \\   __  \\ \\  \\/  / / \\ \\  \\_|/__
              \\ \\  \\_\\\\ \\ \\  \\ \\  \\ \\    / /   \\ \\  \\_|\\ \\
               \\ \\_______\\ \\__\\ \\__\\ \\__/ /     \\ \\_______\\
                \\|_______|\\|__|\\|__|\\|__|/       \\|_______|
            """;

    /**
     * Print a message with a horizontal line above and below it
     * @param msg message to print
     */
    private static void printResponse(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    /**
     * Main entry point
     * @param args any terminal arguments for Forgetful Dave
     */
    public static void main(String[] args) {
        // Show logo at startup
        System.out.println(DAVE_LOGO);

        // Show welcome message
        printResponse("Hello! I'm Duke? Dan? Dave? Something like that...\nHow can I help?");

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        // Initialize item storage
        Tasks tasks = new Tasks();

        /* Store current process state */
        boolean isRunning = true;

        // Main terminal loop
        while (isRunning) {
            String input = scanner.nextLine();
            String[] splitArgs = input.split(" ");

            switch(splitArgs[0]) {
                case "bye":
                    isRunning = false;
                    break;
                case "list":
                    printResponse(String
                            .format("I think you have these tasks:\n%s\nMight have forgotten some though...",
                                    tasks));
                    break;
                case "mark":
                    printResponse(String.format("Checked this task off:\n   %s",
                            tasks.setDone(Integer.parseInt(splitArgs[1]) - 1, true)));
                    break;
                case "unmark":
                    printResponse(String.format("Erased the checkmark:\n   %s",
                            tasks.setDone(Integer.parseInt(splitArgs[1]) - 1, false)));
                    break;
                default:
                    tasks.store(input);
                    printResponse(String.format("added: %s", input));
            }
        }

        // Send goodbye message
        printResponse("See you around!");
    }
}
