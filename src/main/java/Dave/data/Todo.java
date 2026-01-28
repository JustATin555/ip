package Dave.data;

/**
 * Represents a task without any Dave.data or time attached to it
 *
 * @author JustATin555
 * @version 1.0
 */
public class Todo extends Task {

    /**
     * Constructs a new todo.
     *
     * @param description A string describing the todo.
     */
    public Todo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return String.format("[T][%s] %s", isDone ? "X" : " ", description);
    }

    @Override
    public String toStored() {
        return String.format("T | %s | %s", isDone ? "1" : "0", description);
    }
}
