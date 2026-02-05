package Dave.commands;

import Dave.data.Task;
import Dave.data.Tasklist;
import Dave.storage.DiskStore;
import Dave.ui.Ui;

/**
 * Represents a command that creates a todo.
 *
 * @author JustATin555
 * @version 1.1
 */
public class TodoCommand extends Command {

    private final String description;

    /**
     * Constructs a newly parsed Todo command.
     *
     * @param description A string describing the todo.
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
