/**
 * The tasks tracked by the chatbot
 *
 * @author JustATin555
 */
package data;

import java.util.ArrayList;

public class Tasks {

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Store a new todo
     *
     * @param description of the task
     * @return the new task
     */
    public Task store(String description) {
        tasks.add(new Todo(description));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Store a new deadline
     *
     * @param description of the task
     * @param deadline    of the task
     * @return the new task
     */
    public Task store(String description, String deadline) {
        tasks.add(new Deadline(description, deadline));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Store a new event
     *
     * @param description of the task
     * @param start       of the task
     * @param end         of the task
     * @return the new task
     */
    public Task store(String description, String start, String end) {
        tasks.add(new Event(description, start, end));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Mark a task done or not done
     *
     * @param idx    of the task (zero-indexed)
     * @param isDone whether the task is done or not
     * @return the updated task
     */
    public Task setDone(int idx, boolean isDone) {
        return tasks.get(idx).setDone(isDone);
    }

    /**
     * Remove a task
     *
     * @param idx of the task (zero-indexed)
     * @return the deleted task
     */
    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    /**
     * Provide a string representation of stored tasks
     */
    @Override
    public String toString() {
        int size = tasks.size();

        String[] labelled = new String[size];

        for (int i = 0; i < size; i++) {
            labelled[i] = String.format("%d. %s", i + 1, tasks.get(i));
        }

        return String.join("\n", labelled);
    }
}
