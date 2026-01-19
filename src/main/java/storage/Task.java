/**
 * A singular task
 *
 * @author JustATin555
 */
package storage;

public class Task {

    private String description;
    private boolean isDone = false;

    /**
     * Construct a new (not done) task
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Provide a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
