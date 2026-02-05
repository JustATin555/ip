package Dave.commands;

import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a command that lists tasks.
 *
 * @author JustATin555
 * @version 1.0
 */
public class ListCommand extends Command {
    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        ui.display(String.format("""
                I only remember these tasks:
                %s
                Might have forgotten some though...""", tl));
    }
}
