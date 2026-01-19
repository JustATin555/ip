/**
 * A singular task
 *
 * @author JustATin555
 */
package storage;

public abstract class Task {

    protected final String description;
    protected boolean isDone = false;

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
    public abstract String toString();
}
