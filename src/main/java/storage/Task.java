/**
 * A singular task
 *
 * @author JustATin555
 */
package storage;

public class Task {

    private final String description;
    private boolean isDone = false;

    /**
     * Construct a new (not done) task
     * @param description of the task
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Mark a task done or not done
     * @param isDone whether the task is done or not
     * @return a string describing the task
     */
    public Task setDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Provide a string representation of the task
     */
    @Override
    public String toString() {
        return String.format("[%s] %s", isDone ? "X" : " ", description);
    }
}
