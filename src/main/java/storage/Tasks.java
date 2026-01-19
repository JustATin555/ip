/**
 * The tasks tracked by the chatbot
 *
 * @author JustATin555
 */
package storage;

public class Tasks {

    private final String[] tasks = new String[100];
    private int size = 0;

    /**
     * Store a new task
     * @param task to add to the list
     */
    public void store(String task) {
        tasks[size] = task;
        size++;
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
