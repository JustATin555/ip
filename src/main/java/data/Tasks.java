package data;

import java.util.ArrayList;

/**
 * Represents a series of tasks
 *
 * @author JustATin555
 * @version 1.0
 */
public class Tasks {

    private final ArrayList<Task> tasks = new ArrayList<>();

    /**
     * Stores a new todo.
     *
     * @param description A string describing the task.
     * @return The new task.
     */
    public Task store(String description) {
        tasks.add(new Todo(description));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Stores a new deadline.
     *
     * @param description A string describing the task.
     * @param deadline    The task deadline.
     * @return The new task.
     */
    public Task store(String description, String deadline) {
        tasks.add(new Deadline(description, deadline));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Stores a new event.
     *
     * @param description A string describing the task.
     * @param start       When the task starts.
     * @param end         When the task ends.
     * @return The new task.
     */
    public Task store(String description, String start, String end) {
        tasks.add(new Event(description, start, end));
        return tasks.get(tasks.size() - 1);
    }

    /**
     * Marks a task "done" or "not done".
     *
     * @param idx    The task's index (zero-indexed).
     * @param isDone Whether the task is done or not.
     * @return the updated task.
     */
    public Task setDone(int idx, boolean isDone) {
        return tasks.get(idx).setDone(isDone);
    }

    /**
     * Removes a task.
     *
     * @param idx The task's index (zero-indexed).
     * @return The deleted task.
     */
    public Task remove(int idx) {
        return tasks.remove(idx);
    }

    /**
     * Provides a string representation of all stored tasks.
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
