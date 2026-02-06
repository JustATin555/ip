package Dave.commands;

import Dave.data.Task;
import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a command that deletes an existing task.
 *
 * @author JustATin555
 * @version 1.0
 */
public class DeleteCommand extends Command {

    private final int index;

    /**
     * Constructs a newly parsed delete command.
     *
     * @param index The index of the task to delete.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        Task task = tl.remove(index);
        ds.overwrite(tl.getTasks());
        ui.display(String.format("I won't remember this task anymore:\n   %s", task));
    }
}
