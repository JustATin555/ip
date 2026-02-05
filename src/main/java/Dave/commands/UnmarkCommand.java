package Dave.commands;

import Dave.data.Task;
import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

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
        Task task = tl.setDone(index, false);
        ds.overwrite(tl.forStorage());
        ui.display(String.format("Erased this checkmark:\n   %s", task));
    }
}
