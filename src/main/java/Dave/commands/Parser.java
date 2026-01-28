package Dave.commands;

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
    public static final DateTimeFormatter dateTimeInputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** A formatter for datetime outputted by chatbot */
    public static final DateTimeFormatter dateTimeOutputFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy @ HH:mm");

    private static ParsedCommand createInvalid(String warning) {
        return new ParsedCommand(
                CommandType.INVALID,
                new InvalidCommand(warning));
    }

    /**
     * Parses a command that expects a task index as output.
     *
     * @param input      A string representing a mark/unmark/delete command.
     * @param identifier Which command it is.
     * @return A task index, or an invalid command.
     */
    private static ParsedCommand parseIndex(String input, CommandType identifier) {
        try {
            String[] splitArgs = input.split(" ");

            if (splitArgs.length < 2) return createInvalid(
                    "Hmmm, I don't know which index you're talking about.");

            return new ParsedCommand(
                    identifier,
                    new TaskIndex(parseInt(splitArgs[1]) - 1));
        } catch (NumberFormatException exception) {
            return createInvalid(String.format(
                    "Hmmm, I don't know which index \"%s\" refers to.",
                    exception.getMessage()));
        }
    }

    /**
     * Parses a todo command.
     *
     * @param input A string representing a todo command.
     * @return A todo command, or an invalid command.
     */
    private static ParsedCommand parseTodo(String input) {
        return input.length() < 5
                ? createInvalid("Got it, nothing todo.")
                : new ParsedCommand(CommandType.TODO, new TodoCommand(input.substring(5)));
    }

    /**
     * Parses a deadline command.
     *
     * @param input A string representing a deadline command.
     * @return A deadline command, or an invalid command.
     */
    private static ParsedCommand parseDeadline(String input) {
        if (input.length() < 9) return createInvalid("Got it, no deadline to remember.");

        String[] splitArgs = input.substring(9).split(" /");

        if (splitArgs.length != 2 || !splitArgs[1].startsWith("by")) {
            return createInvalid("Hmmm, that doesn't seem like a deadline.");
        }

        try {
            return new ParsedCommand(
                    CommandType.DEADLINE,
                    new DeadlineCommand(
                            splitArgs[0],
                            LocalDateTime.parse(
                                    splitArgs[1].substring(3),
                                    dateTimeInputFormatter)));
        } catch (DateTimeParseException exception) {
            return createInvalid(String.format(
                    "Hmmm, I don't know when \"%s\" refers to.",
                    exception.getParsedString()));
        }
    }

    /**
     * Parses an event command.
     *
     * @param input A string representing an event command.
     * @return An event command, or an invalid command.
     */
    private static ParsedCommand parseEvent(String input) {
        if (input.length() < 6) return createInvalid("Got it, there's no event on.");

        String[] splitArgs = input.substring(6).split(" /");

        if (splitArgs.length != 3 || !splitArgs[1].startsWith("from") || !splitArgs[2].startsWith("to")) {
            return createInvalid("Hmmm, that doesn't seem like an event.");
        }

        try {
            return new ParsedCommand(
                    CommandType.EVENT,
                    new EventCommand(
                            splitArgs[0],
                            LocalDateTime.parse(splitArgs[1].substring(5), dateTimeInputFormatter),
                            LocalDateTime.parse(splitArgs[2].substring(3), dateTimeInputFormatter)));
        } catch (DateTimeParseException exception) {
            return createInvalid(
                    String.format(
                            "Hmmm, I don't know when \"%s\" refers to.",
                            exception.getParsedString()));
        }
    }

    /**
     * Parses a string into a valid or invalid command.
     *
     * @param input A string representing a command.
     * @return A valid or invalid command.
     */
    public static ParsedCommand parseCommand(String input) {
        String[] splitArgs = input.split(" ");

        return switch (splitArgs[0]) {
            case "bye" -> new ParsedCommand(CommandType.BYE, null);
            case "list" -> new ParsedCommand(CommandType.LIST, null);
            case "mark" -> parseIndex(input, CommandType.MARK);
            case "unmark" -> parseIndex(input, CommandType.UNMARK);
            case "todo" -> parseTodo(input);
            case "deadline" -> parseDeadline(input);
            case "event" -> parseEvent(input);
            case "delete" -> parseIndex(input, CommandType.DELETE);
            default -> createInvalid("""
                    Did you forget to start with a command?
                    Don't worry, we all get forgetful sometimes.""");
        };
    }
}
