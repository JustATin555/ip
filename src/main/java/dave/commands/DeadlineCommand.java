package dave.commands;

import java.time.LocalDateTime;

import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents a command that creates a deadline.
 *
 * @author JustATin555
 * @version 1.1
 */
public class DeadlineCommand extends Command {

    private final String description;
    private final LocalDateTime deadline;

    /**
     * Constructs a newly parsed deadline command.
     *
     * @param description A string describing the task.
     * @param deadline    A datetime specifying the deadline.
     */
    public DeadlineCommand(String description, LocalDateTime deadline) {
        this.description = description;
        this.deadline = deadline;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        Task task = tl.store(description, deadline);
        ds.save(task);
        ui.display(String.format("Alright, we'll both try to remember this task:\n   %s", task));
    }
}
