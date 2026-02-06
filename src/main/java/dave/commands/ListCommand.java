package dave.commands;

import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents a command that lists tasks.
 *
 * @author JustATin555
 * @version 1.0
 */
public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        ui.display("I can only remember these tasks:", tl.getTasks());
    }
}
