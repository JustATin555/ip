/**
 * A task without any data or time attached to it
 *
 * @author JustATin555
 */
package data;

public class Todo extends Task {

    /**
     * Construct a new todo task
     *
     * @param description of the task
     */
    public Todo(String description) {
        super(description);
    }

    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", description);
    }
}
