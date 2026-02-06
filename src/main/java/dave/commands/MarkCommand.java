package dave.commands;

import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

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
        int size = tl.getSize();
        if (index >= size) {
            ui.display(String.format("Hmmm, I only remember %d tasks. Which one did you mean?", size));
            return;
        }

        Task task = tl.setDone(index, true);
        ds.overwrite(tl.getTasks());
        ui.display(String.format("Checked this task off:\n   %s", task));
    }
}
