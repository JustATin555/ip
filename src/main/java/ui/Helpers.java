package ui;

/**
 * A collection of various UI helpers.
 *
 * @author JustATin555
 * @version 1.0
 */
public class Helpers {

    /** 3D-ASCII Art generated with https://patorjk.com/software/taag/ */
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
     * Prints a welcome message.
     */
    public static void printWelcome() {
        // Show logo at startup
        System.out.println(DAVE_LOGO);

        // Show welcome message
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Duke? Dan? Dave? Something like that...
                How can I help?
                ____________________________________________________________""");
    }

    /**
     * Prints a message with a horizontal line above and below it.
     *
     * @param msg Message to print.
     */
    public static void prettyPrint(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }
}
