package dave.data;

import static dave.commands.Parser.DATE_TIME_OUTPUT_FORMATTER;

import java.time.LocalDateTime;

/**
 * Represents a task that needs to be done before a specific date or time.
 *
 * @author JustATin555
 * @version 1.1
 */
public class Deadline extends Task {

    /** The task's deadline */
    private final LocalDateTime deadline;

    /**
     * Constructs a new deadline task.
     *
     * @param description A string describing the task.
     */
    public Deadline(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)",
                isDone ? "X" : " ",
                description,
                deadline.format(DATE_TIME_OUTPUT_FORMATTER));
    }

    @Override
    public String toStored() {
        return String.format("D | %s | %s | %s",
                isDone ? "1" : "0",
                description,
                deadline);
    }
}
