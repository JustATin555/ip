package Dave.ui;

import java.nio.file.Path;

import Dave.data.Tasklist;
import Dave.storage.DiskStore;

/**
 * Represents a personal assistant chatbot.
 * Runs in the command line.
 *
 * @author JustATin555
 * @version 1.1
 */
public class ForgetfulDave {

    private final Ui ui;
    private final DiskStore diskstore;
    private final Tasklist tasklist;

    /**
     * Constructs a new chatbot instance.
     *
     * @param filePath A path to the storage file.
     */
    public ForgetfulDave(Ui ui, Path filePath) {
        this.ui = ui;
        this.diskstore = new DiskStore(filePath);
        this.tasklist = new Tasklist(diskstore.load());
    }

    /**
     * Runs the chatbot.
     *
     * @param args Terminal arguments for Forgetful Dave.
     */
    public static void main(String[] args) {
        new ForgetfulDave(
                new CommandLineInterface(),
                Path.of("tasks.txt"))
                .run();
    }

//    case BYE -> {
//        prettyPrint("See you around!");
//        System.exit(0);
//    }
//    case LIST -> prettyPrint(String.format("""
//                    I only remember these tasks:
//                    %s
//                    Might have forgotten some though...""",
//            tasklist));
//    case MARK -> {
//        Task task = tasklist.setDone(((TaskIndex) command.args()).idx(), true);
//        updateStore();
//        prettyPrint(String.format("Checked this task off:\n   %s", task));
//    }
//    case UNMARK -> {
//        Task task = tasklist.setDone(((TaskIndex) command.args()).idx(), false);
//        updateStore();
//        prettyPrint(String.format("Erased this checkmark:\n   %s", task));
//    }
//    case DEADLINE -> {
//        DeadlineCommand cmd = (DeadlineCommand) command.args();
//        Task task = tasklist.store(cmd.description(), cmd.deadline());
//        diskStore.save(task);
//        prettyPrint(String.format("Alright, we'll both try to remember this task:\n   %s", task));
//    }
//    case EVENT -> {
//        EventCommand cmd = (EventCommand) command.args();
//        Task task = tasklist.store(cmd.description(), cmd.start(), cmd.end());
//        diskStore.save(task);
//        prettyPrint(String.format("Alright, we'll both try to remember this task:\n   %s", task));
//    }
//    case DELETE -> {
//        Task task = tasklist.remove(((TaskIndex) command.args()).idx());
//        updateStore();
//        prettyPrint(String.format("I won't remember this task anymore:\n   %s", task));
//    }
//    case FIND -> {
//        prettyPrint(String.format(
//                "Are you talking about these tasks?\n%s",
//                listTasks(tasklist.search(((FindCommand) command.args()).searchString()))));
//    }

    /**
     * Runs a chatbot instance.
     */
    public void run() {
        ui.start();

        while (true) {
            ui.getNextCommand().execute(ui, diskstore, tasklist);
        }
    }

    /**
     * Syncs stored data with current tasklist.
     */
    private void updateStore() {
        diskstore.overwrite(tasklist.forStorage());
    }
}
