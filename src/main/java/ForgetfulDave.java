/**
 * A personal assistant chatbot
 *
 * @author JustATin555
 */
import java.util.Scanner;

import ui.Handler;

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

        // Initialize handler object
        Handler handler = new Handler();

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
                case "mark" -> handler.mark(splitArgs[1]);
                case "unmark" -> handler.unmark(splitArgs[1]);
                case "todo" -> splitArgs[0].length() < input.length()
                        ? handler.todo(input.substring(splitArgs[0].length() + 1))
                        : "Got it, no todo to do.";
                case "deadline" -> splitArgs[0].length() < input.length()
                        ? handler.deadline(input.substring(splitArgs[0].length() + 1))
                        : "Got it, no deadline whenever.";
                case "event" -> splitArgs[0].length() < input.length()
                        ? handler.event(input.substring(splitArgs[0].length() + 1)):
                        "Got it, no event happening whenever.";
                default -> "Did you forget to start with a command? Don't worry, we all get forgetful sometimes.";
            };

            printResponse(result);
        }
    }
}
