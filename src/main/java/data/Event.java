package data;

/**
 * Represents a task that starts at a specific date or time and ends at a specific date or time
 *
 * @author JustATin555
 * @version 1.0
 */
public class Event extends Task {

    /** When the task starts */
    private final String start;

    /** When the task ends */
    private final String end;

    /**
     * Constructs a new deadline task.
     *
     * @param description The task's deadline.
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return String.format("[D][%s] %s (from: %s | to: %s)", isDone ? "X" : " ", description, start, end);
    }
}
