package Dave.ui;

import static Dave.ui.Display.prettyPrint;
import static Dave.ui.Display.printWelcome;

import java.nio.file.Path;
import java.util.Scanner;

import Dave.commands.*;
import Dave.data.Task;
import Dave.data.Tasklist;
import Dave.storage.DiskStore;

/**
 * Represents a personal assistant chatbot.
 * Runs in the command line.
 *
 * @author JustATin555
 * @version 1.0
 */
public class ForgetfulDave {

    private final DiskStore diskStore;
    private final Tasklist tasklist;
    private final Scanner scanner;

    /**
     * Constructs a new chatbot instance.
     *
     * @param filePath A path to the storage file.
     */
    public ForgetfulDave(Path filePath) {
        scanner = new Scanner(System.in);
        diskStore = new DiskStore(filePath);
        tasklist = new Tasklist(diskStore.load());
    }

    /**
     * Runs the chatbot.
     *
     * @param args Terminal arguments for Forgetful Dave.
     */
    public static void main(String[] args) {
        new ForgetfulDave(Path.of("tasks.txt")).run();
    }

    /**
     * Handles a single command.
     *
     * @param command The command to run.
     */
    private void handle(ParsedCommand command) {
        switch (command.identifier()) {
        case INVALID -> prettyPrint(((InvalidCommand) command.args()).warning());
        case BYE -> {
            prettyPrint("See you around!");
            System.exit(0);
        }
        case LIST -> prettyPrint(String.format("""
                        I only remember these tasks:
                        %s
                        Might have forgotten some though...""",
                tasklist));
        case MARK -> {
            Task task = tasklist.setDone(((TaskIndex) command.args()).idx(), true);
            updateStore();
            prettyPrint(String.format("Checked this task off:\n   %s", task));
        }
        case UNMARK -> {
            Task task = tasklist.setDone(((TaskIndex) command.args()).idx(), false);
            updateStore();
            prettyPrint(String.format("Erased this checkmark:\n   %s", task));
        }
        case TODO -> {
            Task task = tasklist.store(((TodoCommand) command.args()).description());
            diskStore.save(task);
            prettyPrint(String.format("Alright, we'll both try to remember this task:\n   %s", task));
        }
        case DEADLINE -> {
            DeadlineCommand cmd = (DeadlineCommand) command.args();
            Task task = tasklist.store(cmd.description(), cmd.deadline());
            diskStore.save(task);
            prettyPrint(String.format("Alright, we'll both try to remember this task:\n   %s", task));
        }
        case EVENT -> {
            EventCommand cmd = (EventCommand) command.args();
            Task task = tasklist.store(cmd.description(), cmd.start(), cmd.end());
            diskStore.save(task);
            prettyPrint(String.format("Alright, we'll both try to remember this task:\n   %s", task));
        }
        case DELETE -> {
            Task task = tasklist.remove(((TaskIndex) command.args()).idx());
            updateStore();
            prettyPrint(String.format("I won't remember this task anymore:\n   %s", task));
        }
        }
    }

    /**
     * Runs a chatbot instance.
     */
    public void run() {
        printWelcome();

        while (true) {
            String input = scanner.nextLine();
            ParsedCommand command = Parser.parseCommand(input);
            handle(command);
        }
    }

    /**
     * Syncs stored data with current tasklist.
     */
    private void updateStore() {
        diskStore.overwrite(tasklist.forStorage());
    }
}
