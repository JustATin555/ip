package Dave.commands;

import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a parsed command (with params).
 *
 * @author JustATin555
 * @version 1.0
 */
public abstract class Command {

    /**
     * Execute the stored command.
     *
     * @param ui        A user interface for command input and response output.
     * @param diskstore A storage handler handling task persistence.
     * @param tasklist  A list storing tasks.
     */
    public abstract void execute(Ui ui, DiskStore diskstore, Tasklist tasklist);
}
