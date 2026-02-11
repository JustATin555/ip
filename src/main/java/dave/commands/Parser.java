package dave.commands;

import static java.lang.Integer.parseInt;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * A parser that converts string commands into valid commands for the handler.
 *
 * @author JustATin555
 * @version 1.0
 */
public class Parser {

    /** A formatter for datetime inputted via CLI */
    public static final DateTimeFormatter DATE_TIME_INPUT_FORMATTER =
            DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** A formatter for datetime outputted by chatbot */
    public static final DateTimeFormatter DATE_TIME_OUTPUT_FORMATTER =
            DateTimeFormatter.ofPattern("MMM dd, yyyy @ HH:mm");

    /**
     * Parses a command that expects a task index as output.
     *
     * @param input A string representing a mark/unmark/delete command.
     * @param cmd   Which command it is.
     * @return A task index, or an invalid command.
     */
    private static Command parseIndex(String input, IndexCommand cmd) {
        String[] splitArgs = input.split(" ");

        if (splitArgs.length < 2) {
            return new InvalidCommand("Hmmm, I don't know which index you're talking about.");
        }

        int idx;
        try {
            idx = parseInt(splitArgs[1]) - 1;
        } catch (NumberFormatException exception) {
            return new InvalidCommand(String.format(
                    "Hmmm, I don't know which index \"%s\" refers to.",
                    exception.getMessage()));
        }

        return switch (cmd) {
            case MARK -> new MarkCommand(idx);
            case UNMARK -> new UnmarkCommand(idx);
            case DELETE -> new DeleteCommand(idx);
        };
    }

    /**
     * Parses a todo command.
     *
     * @param input A string representing a todo command.
     * @return A todo command, or an invalid command.
     */
    private static Command parseTodo(String input) {
        return input.length() < 5
                ? new InvalidCommand("Got it, nothing todo.")
                : new TodoCommand(input.substring(5));
    }

    /**
     * Parses a deadline command.
     *
     * @param input A string representing a deadline command.
     * @return A deadline command, or an invalid command.
     */
    private static Command parseDeadline(String input) {
        if (input.length() < 9) {
            return new InvalidCommand("Got it, no deadline to remember.");
        }

        String[] splitArgs = input.substring(9).split(" /");

        if (splitArgs.length != 2 || !splitArgs[1].startsWith("by")) {
            return new InvalidCommand("Hmmm, that doesn't seem like a deadline.");
        }

        LocalDateTime deadline;
        try {
            deadline = LocalDateTime.parse(
                    splitArgs[1].substring(3),
                    DATE_TIME_INPUT_FORMATTER);
        } catch (DateTimeParseException exception) {
            return new InvalidCommand(String.format(
                    "Hmmm, I don't know when \"%s\" refers to.",
                    exception.getParsedString()));
        }

        return new DeadlineCommand(splitArgs[0], deadline);
    }

    /**
     * Parses an event command.
     *
     * @param input A string representing an event command.
     * @return An event command, or an invalid command.
     */
    private static Command parseEvent(String input) {
        if (input.length() < 6) {
            return new InvalidCommand("Got it, there's no event on.");
        }

        String[] splitArgs = input.substring(6).split(" /");

        if (splitArgs.length != 3
                || !splitArgs[1].startsWith("from")
                || !splitArgs[2].startsWith("to")) {
            return new InvalidCommand("Hmmm, that doesn't seem like an event.");
        }

        LocalDateTime start;
        LocalDateTime end;
        try {
            start = LocalDateTime.parse(splitArgs[1].substring(5), DATE_TIME_INPUT_FORMATTER);
            end = LocalDateTime.parse(splitArgs[2].substring(3), DATE_TIME_INPUT_FORMATTER);
        } catch (DateTimeParseException exception) {
            return new InvalidCommand(String.format(
                    "Hmmm, I don't know when \"%s\" refers to.",
                    exception.getParsedString()));
        }

        return new EventCommand(splitArgs[0], start, end);
    }

    /**
     * Parses a find command.
     *
     * @param input A string representing a find command.
     * @return A find command, or an invalid command.
     */
    private static Command parseFind(String input) {
        return input.length() < 5
                ? new InvalidCommand("Got it, nothing to find.")
                : new FindCommand(input.substring(5));
    }

    /**
     * Parses a string into a valid or invalid command.
     *
     * @param input A string representing a command.
     * @return A valid or invalid command.
     */
    public static Command parseCommand(String input) {
        String[] splitArgs = input.split(" ");

        return switch (splitArgs[0]) {
            case "bye" -> new ExitCommand();
            case "list" -> new ListCommand();
            case "mark" -> parseIndex(input, IndexCommand.MARK);
            case "unmark" -> parseIndex(input, IndexCommand.UNMARK);
            case "todo" -> parseTodo(input);
            case "deadline" -> parseDeadline(input);
            case "event" -> parseEvent(input);
            case "delete" -> parseIndex(input, IndexCommand.DELETE);
            case "find" -> parseFind(input);
            default -> new InvalidCommand("""
                    Did you forget to start with a command?
                    Don't worry, we all get forgetful sometimes.""");
        };
    }

    /** Represents commands that accept a single index */
    private enum IndexCommand {
        MARK,
        UNMARK,
        DELETE
    }
}
