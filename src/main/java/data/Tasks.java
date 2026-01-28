package data;

import java.nio.file.Path;
import java.util.ArrayList;

import storage.DiskStore;

/**
 * Represents a series of tasks
 *
 * @author JustATin555
 * @version 1.0
 */
public class Tasks {

    private final ArrayList<Task> tasks;
    private final DiskStore ds;

    public Tasks(Path filePath) {
        this.ds = new DiskStore(filePath);
        this.tasks = ds.load();
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
    public int store(String description) {
        Todo task = new Todo(description);
        tasks.add(task);
        ds.save(task);
        return tasks.size() - 1;
    }

    /**
     * Stores a new deadline.
     *
     * @param description A string describing the task.
     * @param deadline    The task deadline.
     * @return The new task.
     */
    public int store(String description, String deadline) {
        Deadline task = new Deadline(description, deadline);
        tasks.add(task);
        ds.save(task);
        return tasks.size() - 1;
    }

    /**
     * Stores a new event.
     *
     * @param description A string describing the task.
     * @param start       When the task starts.
     * @param end         When the task ends.
     * @return The new task.
     */
    public int store(String description, String start, String end) {
        Event task = new Event(description, start, end);
        tasks.add(task);
        ds.save(task);
        return tasks.size() - 1;
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
        ds.overwrite(tasks);
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
        ds.overwrite(tasks);
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
}
