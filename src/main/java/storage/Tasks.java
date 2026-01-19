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
     * Store a new task
     * @param description of the task
     */
    public void store(String description) {
        tasks[size] = new Task(description);
        size++;
    }

    /**
     * Provide a string representation of stored tasks
     */
    @Override
    public String toString() {
        String[] labelled = new String[size];

        for (int i = 0; i < size; i++) {
            labelled[i] = String.format("%d.%s", i + 1, tasks[i].toString());
        }

        return String.join("\n", labelled);
    }
}
