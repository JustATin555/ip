package dave.commands;

import java.util.List;

import dave.data.Task;
import dave.data.Tasklist;
import dave.storage.DiskStore;
import dave.ui.Ui;

/**
 * Represents a command that lists reminders.
 *
 * @author JustATin555
 * @version 1.0
 */
public class RemindersCommand extends Command {
    @Override
    public void execute(Ui ui, DiskStore ds, Tasklist tl) {
        List<Task> tasks = tl.getReminders();

        if (tasks.isEmpty()) {
            ui.display("You have no upcoming tasks");
            return;
        }

        ui.display("You have these upcoming tasks:", tasks);
    }
}
