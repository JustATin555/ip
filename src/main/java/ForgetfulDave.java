import java.nio.file.Path;
import java.util.Scanner;

import commands.ParsedCommand;
import ui.Handler;
import ui.Parser;

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
     * Runs the chatbot.
     *
     * @param args Terminal arguments for Forgetful Dave.
     */
    public static void main(String[] args) {
        // Show logo at startup
        System.out.println(DAVE_LOGO);

        // Show welcome message
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Duke? Dan? Dave? Something like that...
                How can I help?
                ____________________________________________________________""");

        // Initialize parser and handler
        Handler handler = new Handler(Path.of("tasks.txt"));

        // Create scanner to read user input
        Scanner scanner = new Scanner(System.in);

        /* Store current process state */
        boolean isRunning = true;

        // Main terminal loop
        while (isRunning) {
            // Parse input
            String input = scanner.nextLine();

            ParsedCommand cmd = Parser.parseCommand(input);

            handler.handle(cmd);
        }
    }
}
