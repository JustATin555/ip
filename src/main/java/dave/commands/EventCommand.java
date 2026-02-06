package dave.commands;

import java.time.LocalDateTime;

import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents a command that creates an event.
 *
 * @author JustATin555
 * @version 1.1
 */
public class EventCommand extends Command {

    private final String description;
    private final LocalDateTime start;
    private final LocalDateTime end;

    /**
     * Constructs a newly parsed event command.
     *
     * @param description A string describing the task.
     * @param start       A datetime describing when the event starts.
     * @param end         A datetime describing when the event ends.
     */
    public EventCommand(String description, LocalDateTime start, LocalDateTime end) {
        this.description = description;
        this.start = start;
        this.end = end;
    }

    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        Task task = tl.store(description, start, end);
        ds.save(task);
        ui.display(String.format("Alright, we'll both try to remember this task:\n   %s", task));
    }
}
