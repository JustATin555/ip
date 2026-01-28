package commands;

/**
 * Represents an invalid command.
 *
 * @param warning An explanation of why the command is invalid.
 */
public record InvalidCommand(
        String warning
) {
}
