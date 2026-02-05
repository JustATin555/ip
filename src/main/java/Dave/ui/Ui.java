package Dave.ui;

import Dave.commands.Command;

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
     * Waits for the next command inputted into the interface.
     *
     * @return The next command.
     */
    public abstract Command getNextCommand();
}
