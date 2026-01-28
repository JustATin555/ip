package ui;

import java.nio.file.Path;

import commands.*;
import data.Tasks;

/**
 * A collection of command handlers bundled together.
 *
 * @author JustATin555
 * @version 1.0
 */
public class Handler {

    private final Tasks tasks;

    /**
     * Constructs a new command handler
     */
    public Handler(Path filePath) {
        this.tasks = new Tasks(filePath);
    }

    /**
     * Prints a message with a horizontal line above and below it.
     *
     * @param msg Message to print.
     */
    private static void printResponse(String msg) {
        System.out.println("____________________________________________________________");
        System.out.println(msg);
        System.out.println("____________________________________________________________");
    }

    public void handle(ParsedCommand command) {
        switch (command.identifier()) {
        case INVALID -> printResponse(((InvalidCommand) command.args()).warning());
        case BYE -> {
            printResponse("See you around!");
            System.exit(0);
        }
        case LIST -> printResponse(String.format("""
                        I only remember these tasks:
                        %s
                        Might have forgotten some though...""",
                tasks));
        case MARK -> printResponse(String.format("""
                        Checked this task off:
                           %s""",
                tasks.setDone(
                        ((TaskIndex) command.args()).idx(),
                        true)));
        case UNMARK -> printResponse(String.format("""
                        Erased this checkmark:
                           %s""",
                tasks.setDone(
                        ((TaskIndex) command.args()).idx(),
                        false)));
        case TODO -> printResponse(String.format("""
                        Alright, we'll both try to remember this task:
                           %s""",
                tasks.get(tasks.store(((TodoCommand) command.args()).description()))));
        case DEADLINE -> {
            DeadlineCommand cmd = (DeadlineCommand) command.args();
            printResponse(String.format("""
                            Alright, we'll both try to remember this task:
                               %s""",
                    tasks.get(tasks.store(cmd.description(), cmd.deadline()))));
        }
        case EVENT -> {
            EventCommand cmd = (EventCommand) command.args();
            printResponse(String.format("""
                            Alright, we'll both try to remember this task:
                               %s""",
                    tasks.get(tasks.store(cmd.description(), cmd.start(), cmd.end()))));
        }
        case DELETE -> printResponse(String.format("""
                        I won't remember this task anymore:
                           %s""",
                tasks.remove(((TaskIndex) command.args()).idx())));
        }
    }
}
