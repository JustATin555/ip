/**
 * The tasks tracked by the chatbot
 *
 * @author JustATin555
 */
package storage;

public class Tasks {

    private final Task[] tasks = new Task[100];
    private int size = 0;

    /**
     * Store a new todo task
     * @param description of the task
     */
    public Task store(String description) {
        tasks[size] = new TodoTask(description);
        return tasks[size++];
    }

    /**
     * Mark a task done or not done
     * @param idx of the task (zero-indexed)
     * @param isDone whether the task is done or not
     * @return a string describing the task
     */
    public Task setDone(int idx, boolean isDone) {
        return tasks[idx].setDone(isDone);
    }

    /**
     * Provide a string representation of stored tasks
     */
    @Override
    public String toString() {
        String[] labelled = new String[size];

        for (int i = 0; i < size; i++) {
            labelled[i] = String.format("%d. %s", i + 1, tasks[i]);
        }

        return String.join("\n", labelled);
    }
}
