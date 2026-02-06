package dave.commands;

import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents a command that creates a todo.
 *
 * @author JustATin555
 * @version 1.1
 */
public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a newly parsed todo command.
     *
     * @param description A string describing the task.
     */
    public TodoCommand(String description) {
        this.description = description;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        Task task = tl.store(description);
        ds.save(task);
        ui.display(String.format("Alright, we'll both try to remember this task:\n   %s", task));
    }
}
