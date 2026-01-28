package commands;

/**
 * Represents a command that marks a task done.
 *
 * @param idx The index of the task.
 */
public record UnmarkCommand(
        int idx
) {
}
