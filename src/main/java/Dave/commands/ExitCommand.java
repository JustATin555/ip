package Dave.commands;

import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a command that exits the chatbot.
 *
 * @author JustATin555
 * @version 1.0
 */
public class ExitCommand extends Command {

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        ui.display("See you around!");
        System.exit(0);
    }
}
