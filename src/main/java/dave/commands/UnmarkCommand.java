package dave.commands;

import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents a command that marks a task as "not done".
 *
 * @author JustATin555
 * @version 1.0
 */
public class UnmarkCommand extends Command {

    private final int index;

    /**
     * Constructs a newly parsed unmark command.
     *
     * @param index The index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        // Guard against invalid index
        int size = tl.getSize();
        if (index < 0 || index >= size) {
            ui.display(String.format("Hmmm, I only remember %d tasks. Which one did you mean?", size));
            return;
        }

        // Mark task as not done
        Task task = tl.setDone(index, false);
        ds.overwrite(tl.getTasks());
        ui.display(String.format("Erased this checkmark:\n   %s", task));
    }
}
