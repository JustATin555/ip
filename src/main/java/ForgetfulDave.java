import java.nio.file.Path;
import java.util.Scanner;

import storage.DiskStore;
import ui.Handler;

/**
 * Represents a personal assistant chatbot.
 * Runs in the command line.
 *
 * @author JustATin555
 * @version 1.0
 */
public class ForgetfulDave {
    /* 3D-ASCII Art generated with https://patorjk.com/software/taag/ */
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
     * Prints a message with a horizontal line above and below it.
     *
     * @param msg Message to print.
     */
    private static void printResponse(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    /**
     * Runs the chatbot.
     *
     * @param args Terminal arguments for Forgetful Dave.
     */
    public static void main(String[] args) {
        // Show logo at startup
        System.out.println(DAVE_LOGO);

        // Show welcome message
        printResponse("Hello! I'm Duke? Dan? Dave? Something like that...\nHow can I help?");

        // Initialize disk storage
        DiskStore ds = new DiskStore(Path.of("tasks.txt"));

        // Initialize handler object
        Handler handler = new Handler(ds);

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        /* Store current process state */
        boolean isRunning = true;

        // Main terminal loop
        while (isRunning) {
            // Parse input
            String input = scanner.nextLine();
            String[] splitArgs = input.split(" ");

            // Run relevant handler
            String result = switch (splitArgs[0]) {
                case "bye" -> {
                    isRunning = false;
                    yield "See you around!";
                }
                case "list" -> handler.list();
                case "mark" -> splitArgs.length > 1
                        ? handler.mark(splitArgs[1])
                        : "Which task should I mark?";
                case "unmark" -> splitArgs.length > 1
                        ? handler.unmark(splitArgs[1])
                        : "Which task should I unmark?";
                case "todo" -> splitArgs[0].length() < input.length()
                        ? handler.todo(input.substring(splitArgs[0].length() + 1))
                        : "Got it, no todo to do.";
                case "deadline" -> splitArgs[0].length() < input.length()
                        ? handler.deadline(input.substring(splitArgs[0].length() + 1))
                        : "Got it, no deadline whenever.";
                case "event" -> splitArgs[0].length() < input.length()
                        ? handler.event(input.substring(splitArgs[0].length() + 1)) :
                        "Got it, no event happening whenever.";
                case "delete" -> splitArgs.length > 1
                        ? handler.delete(splitArgs[1])
                        : "Which task should I delete?";
                default -> "Did you forget to start with a command?\nDon't worry, we all get forgetful sometimes.";
            };

            printResponse(result);
        }
    }
}
