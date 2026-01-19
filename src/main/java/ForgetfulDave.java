/**
 * A personal assistant chatbot
 *
 * @author JustATin555
 */

import java.util.Scanner;

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
        Scanner scan = new Scanner(System.in);

        // Main terminal loop
        while (true) {
            // Read user input
            String input = scan.nextLine();

            // Stop on "bye"
            if (input.equals("bye")) break;

            // Otherwise, echo the command
            printResponse(input);
        }

        // Send goodbye message
        printResponse("See you around!");
    }
}
