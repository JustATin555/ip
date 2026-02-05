package Dave.commands;

import Dave.data.Task;
import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a command that marks a task as "done".
 *
 * @author JustATin555
 * @version 1.0
 */
public class MarkCommand extends Command {

    private final int index;

    /**
     * Constructs a newly parsed mark command.
     *
     * @param index The index of the task to mark.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        Task task = tl.setDone(index, true);
        ds.overwrite(tl.forStorage());
        ui.display(String.format("Checked this task off:\n   %s", task));
    }
}
