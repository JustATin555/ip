package Dave.data;

import java.nio.file.Path;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Dave.storage.DiskStore;

/**
 * Represents a series of tasks
 *
 * @author JustATin555
 * @version 1.1
 */
public class Tasklist {

    private final ArrayList<Task> tasks;

    /**
     * Constructs a new tasklist containing the given tasks.
     *
     * @param tasks The initial tasks in the tasklist.
     */
    public Tasklist(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Gets task stored at the given index
     *
     * @param idx The index of the stored task.
     * @return The stored task.
     */
    public Task get(int idx) {
        return tasks.get(idx);
    }

    /**
     * Stores a new todo.
     *
     * @param description A string describing the task.
     * @return The new task.
     */
    public Task store(String description) {
        Todo task = new Todo(description);
        tasks.add(task);
        return task;
    }

    /**
     * Stores a new deadline.
     *
     * @param description A string describing the task.
     * @param deadline    The task deadline.
     * @return The new task.
     */
    public Task store(String description, LocalDateTime deadline) {
        Deadline task = new Deadline(description, deadline);
        tasks.add(task);
        return task;
    }

    /**
     * Stores a new event.
     *
     * @param description A string describing the task.
     * @param start       When the task starts.
     * @param end         When the task ends.
     * @return The new task.
     */
    public Task store(String description, LocalDateTime start, LocalDateTime end) {
        Event task = new Event(description, start, end);
        tasks.add(task);
        return task;
    }

    /**
     * Marks a task "done" or "not done".
     *
     * @param idx    The task's index (zero-indexed).
     * @param isDone Whether the task is done or not.
     * @return the updated task.
     */
    public Task setDone(int idx, boolean isDone) {
        Task task = tasks.get(idx).setDone(isDone);
        return task;
    }

    /**
     * Removes a task.
     *
     * @param idx The task's index (zero-indexed).
     * @return The deleted task.
     */
    public Task remove(int idx) {
        Task task = tasks.remove(idx);
        return task;
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

    /**
     * Provides an array list for storage.
     */
    public ArrayList<Task> forStorage() {
        return tasks;
    }
}
