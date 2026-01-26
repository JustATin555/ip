/**
 * A task that needs to be done before a specific date or time
 *
 * @author JustATin555
 */
package data;

public class Deadline extends Task {

    /* The task's deadline */
    private final String deadline;

    /**
     * Construct a new deadline task
     *
     * @param description of the task
     */
    public Deadline(String description, String deadline) {
        super(description);
        this.deadline = deadline;
    }

    public String toString() {
        return String.format("[D][%s] %s (by: %s)", isDone ? "X" : " ", description, deadline);
    }
}
