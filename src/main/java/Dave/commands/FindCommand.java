package Dave.commands;

/**
 * Represents a command finds tasks containing a string.
 *
 * @param searchString The string to find within the task description.
 */

public record FindCommand(
        String searchString
) {
}
