package Dave.data;

/**
 * Represents a singular task.
 *
 * @author JustATin555
 * @version 1.0
 */
public abstract class Task {

    /** A string describing the task */
    protected final String description;

    /** Whether the task is done or not */
    protected boolean isDone = false;

    /**
     * Constructs a new (not done) task.
     *
     * @param description A string describing the task.
     */
    public Task(String description) {
        this.description = description;
    }

    /**
     * Marks a task "done" or "not done".
     *
     * @param isDone Whether the task is done or not.
     * @return A string describing the task.
     */
    public Task setDone(boolean isDone) {
        this.isDone = isDone;
        return this;
    }

    /**
     * Provides a string representation of the task.
     */
    @Override
    public abstract String toString();

    /**
     * Provides a string representation of the task for storage as plaintext.
     */
    public abstract String toStored();
}
