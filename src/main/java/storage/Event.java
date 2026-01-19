/**
 * A task that starts at a specific date or time and ends at a specific date or time
 */
package storage;

public class Event extends Task {

    /* The task's start and end */
    private final String start;
    private final String end;

    /**
     * Construct a new deadline task
     * @param description of the task
     */
    public Event(String description, String start, String end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public String toString() {
        return String.format("[D][%s] %s (from: %s | to: %s)", isDone ? "X" : " ", description, start, end);
    }
}
