package ui;

import static ui.Helpers.prettyPrint;
import static ui.Helpers.printWelcome;

import java.nio.file.Path;
import java.util.Scanner;

import commands.*;
import data.Tasklist;

/**
 * Represents a personal assistant chatbot.
 * Runs in the command line.
 *
 * @author JustATin555
 * @version 1.0
 */
public class ForgetfulDave {

    private final Tasklist tasklist;
    private final Scanner scanner;

    /**
     * Constructs a new chatbot instance.
     *
     * @param filePath A path to the storage file.
     */
    public ForgetfulDave(Path filePath) {
        tasklist = new Tasklist(filePath);
        scanner = new Scanner(System.in);
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
     * Handle a single command
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
        case MARK -> prettyPrint(String.format("""
                        Checked this task off:
                           %s""",
                tasklist.setDone(
                        ((TaskIndex) command.args()).idx(),
                        true)));
        case UNMARK -> prettyPrint(String.format("""
                        Erased this checkmark:
                           %s""",
                tasklist.setDone(
                        ((TaskIndex) command.args()).idx(),
                        false)));
        case TODO -> prettyPrint(String.format("""
                        Alright, we'll both try to remember this task:
                           %s""",
                tasklist.get(tasklist.store(((TodoCommand) command.args()).description()))));
        case DEADLINE -> {
            DeadlineCommand cmd = (DeadlineCommand) command.args();
            prettyPrint(String.format("""
                            Alright, we'll both try to remember this task:
                               %s""",
                    tasklist.get(tasklist.store(cmd.description(), cmd.deadline()))));
        }
        case EVENT -> {
            EventCommand cmd = (EventCommand) command.args();
            prettyPrint(String.format("""
                            Alright, we'll both try to remember this task:
                               %s""",
                    tasklist.get(tasklist.store(cmd.description(), cmd.start(), cmd.end()))));
        }
        case DELETE -> prettyPrint(String.format("""
                        I won't remember this task anymore:
                           %s""",
                tasklist.remove(((TaskIndex) command.args()).idx())));
        }
    }

    /**
     * Runs a chatbot instance
     */
    public void run() {
        printWelcome();

        while (true) {
            String input = scanner.nextLine();
            ParsedCommand command = Parser.parseCommand(input);
            handle(command);
        }
    }
}
