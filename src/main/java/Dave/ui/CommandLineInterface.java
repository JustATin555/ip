package Dave.ui;

import java.util.List;
import java.util.Scanner;

import Dave.commands.Command;
import Dave.commands.Parser;
import Dave.data.Task;

/**
 * A collection of various UI helpers.
 *
 * @author JustATin555
 * @version 1.0
 */
public class CommandLineInterface extends Ui {

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

    /** A command line scanner used to read commands */
    private final Scanner scanner = new Scanner(System.in);

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

    @Override
    public void start() {
        // Show logo at startup
        System.out.println(DAVE_LOGO);

        // Show welcome message
        System.out.println("""
                ____________________________________________________________
                Hello! I'm Duke? Dan? Dave? Something like that...
                How can I help?
                ____________________________________________________________""");
    }

    @Override
    public void display(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    @Override
    public Command getNextCommand() {
        return Parser.parseCommand(scanner.nextLine());
    }
}
