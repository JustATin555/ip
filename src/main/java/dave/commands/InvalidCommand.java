package dave.commands;

import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents an invalid command.
 *
 * @author JustATin555
 * @version 1.1
 */
public class InvalidCommand extends Command {

    private final String description;

    /**
     * Constructs an "invalid" command.
     *
     * @param description A string describing why the command was invalid.
     */
    public InvalidCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        ui.display(description);
    }
}
