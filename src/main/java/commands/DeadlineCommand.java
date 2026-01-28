package commands;

import java.time.LocalDateTime;

/**
 * Represents a command that creates a deadline.
 *
 * @param description A string describing the task.
 * @param deadline    A datetime specifying the deadline.
 */
public record DeadlineCommand(
        String description,
        LocalDateTime deadline
) {
}
