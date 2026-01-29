package Dave.ui;

import Dave.data.Task;

import java.util.List;

/**
 * A collection of various UI helpers.
 *
 * @author JustATin555
 * @version 1.0
 */
public class Display {

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

    /**
     * Combines a list of tasks into a single string.
     *
     * @param tasks A list of tasks.
     * @return A string containing the tasks in order.
     */
    public static String listTasks(List<Task> tasks) {
        int size = tasks.size();

        String[] labelled = new String[size];

        for (int i = 0; i < size; i++) {
            labelled[i] = String.format("%d. %s", i + 1, tasks.get(i));
        }

        return String.join("\n", labelled);
    }
}
