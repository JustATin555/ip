package Dave.data;

import static Dave.commands.Parser.dateTimeOutputFormatter;

import java.time.LocalDateTime;

/**
 * Represents a task that starts at a specific date or time and ends at a specific date or time
 *
 * @author JustATin555
 * @version 1.1
 */
public class Event extends Task {

    /** When the task starts */
    private final LocalDateTime start;

    /** When the task ends */
    private final LocalDateTime end;

    /**
     * Constructs a new deadline task.
     *
     * @param description The task's deadline.
     */
    public Event(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[E][%s] %s (from: %s | to: %s)",
                isDone ? "X" : " ",
                description,
                start.format(dateTimeOutputFormatter),
                end.format(dateTimeOutputFormatter));
    }

    @Override
    public String toStored() {
        return String.format("E | %s | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                start,
                end);
    }
}
