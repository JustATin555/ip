package Dave.commands;

import java.time.LocalDateTime;

/**
 * Represents a command that creates an event.
 *
 * @param description A string describing the task.
 * @param start       A datetime describing when the event starts.
 * @param end         A datetime describing when the event ends.
 */
public record EventCommand(
        String description,
        LocalDateTime start,
        LocalDateTime end
) {
}
