package Dave.commands;

import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a command finds tasks containing a string.
 */

public class FindCommand extends Command {

    private final String searchString;

    /**
     * Constructs a newly parsed find command
     *
     * @param searchString The string to find within the task description.
     */
    public FindCommand(String searchString) {
        this.searchString = searchString;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        ui.display("Are you talking about these tasks?", tl.getTasks(searchString));
    }
}
