/**
 * A task without any data or time attached to it
 *
 * @author JustATin555
 */
package storage;

public class TodoTask extends Task {

    /**
     * Construct a new todo task
     * @param description of the task
     */
    public TodoTask(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", description);
    }
}
