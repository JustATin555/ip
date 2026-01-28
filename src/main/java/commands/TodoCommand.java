package commands;

/**
 * Represents a command that creates a todo.
 *
 * @param description A string describing the task.
 */
public record TodoCommand(
        String description
) {
}
