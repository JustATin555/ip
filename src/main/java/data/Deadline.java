package data;

/**
 * Represents a task that needs to be done before a specific date or time
 *
 * @author JustATin555
 * @version 1.0
 */
public class Deadline extends Task {

    /** The task's deadline */
    private final String deadline;

    /**
     * Constructs a new deadline task.
     *
     * @param description A string describing the task.
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", description, deadline);
    }

    @Override
    public String toStored() {
        return String.format("D | %s | %s | %s", isDone ? "1" : "0", description, deadline);
    }
}
