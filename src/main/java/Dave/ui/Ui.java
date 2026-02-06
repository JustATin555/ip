package Dave.ui;

import java.util.List;

import Dave.commands.Command;
import Dave.data.Task;

/**
 * Provides an interface for Dave to interact with the user.
 *
 * @author JustATin555
 * @version 1.0
 */
public abstract class Ui {

    /**
     * Starts the interface.
     */
    public abstract void start();

    /**
     * Displays an arbitrary message.
     *
     * @param message The message to display.
     */
    public abstract void display(String message);


    /**
     * Displays an arbitrary message with a list of tasks.
     *
     * @param message The message to display.
     * @param tasks   A list of tasks to display.
     */
    public abstract void display(String message, List<Task> tasks);

    /**
     * Waits for the next command inputted into the interface.
     *
     * @return The next command.
     */
    public abstract Command getNextCommand();
}
