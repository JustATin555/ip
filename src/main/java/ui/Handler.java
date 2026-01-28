package ui;

import java.nio.file.Path;

import data.Tasks;

/**
 * A collection of command handlers bundled together.
 *
 * @author JustATin555
 * @version 1.0
 */
public class Handler {

    private final Tasks tasks;

    /**
     * Constructs a new command handler
     */
    public Handler(Path filePath) {
        this.tasks = new Tasks(filePath);
    }

    /**
     * Returns a list of all stored tasks.
     *
     * @return A string describing the list of tasks.
     */
    public String list() {
        return String.format("I only remember these tasks:\n%s\nMight have forgotten some though...", tasks);
    }

    /**
     * Marks a task as "done".
     *
     * @param stringIndex Index of the task as a string.
     * @return A description of the marked task.
     */
    public String mark(String stringIndex) {
        try {
            int idx = Integer.parseInt(stringIndex) - 1;
            return String.format("Checked this task off:\n   %s", tasks.setDone(idx, true));
        } catch (NumberFormatException e) {
            return String.format("Hmm, I dunno which task you're talking about. What do you mean by \"%s?\"",
                    stringIndex);
        }
    }

    /**
     * Marks a task as "not done".
     *
     * @param stringIndex Index of the task as a string.
     * @return A description of the unmarked task.
     */
    public String unmark(String stringIndex) {
        try {
            int idx = Integer.parseInt(stringIndex) - 1;
            return String.format("Erased the checkmark:\n   %s", tasks.setDone(idx, false));
        } catch (NumberFormatException e) {
            return String.format("Hmm, I dunno which task you're talking about. What do you mean by \"%s?\"",
                    stringIndex);
        }
    }

    /**
     * Creates a todo.
     *
     * @param description Description of the task as a string.
     * @return A description of the task.
     */
    public String todo(String description) {
        int idx = tasks.store(description);
        return String.format("Alright, we'll both try to remember this task:\n   %s", tasks.get(idx));
    }

    /**
     * Creates a deadline.
     *
     * @param taskInfo The description and deadline of the task as a single string.
     * @return A description of the task.
     */
    public String deadline(String taskInfo) {
        String[] splitArgs = taskInfo.split(" /by ");

        if (splitArgs.length != 2) return "Hmmm, that doesn't seem like a deadline.";

        int idx = tasks.store(splitArgs[0], splitArgs[1]);
        return String.format("Alright, we'll both try to remember this task:\n   %s", tasks.get(idx));
    }

    /**
     * Creates an event.
     *
     * @param taskInfo The description, start and end of the task as a single string.
     * @return A description of the task.
     */
    public String event(String taskInfo) {
        String[] splitArgs = taskInfo.split(" /from ");

        if (splitArgs.length != 2) return "Hmmm, that doesn't seem like an event.";

        String[] splitTimes = splitArgs[1].split(" /to ");

        if (splitTimes.length != 2) return "Hmmm, that doesn't seem like an event.";

        int idx = tasks.store(splitArgs[0], splitTimes[0], splitTimes[1]);
        return String.format("Alright, we'll both try to remember this task:\n   %s", tasks.get(idx));
    }

    /**
     * Deletes an existing task.
     *
     * @param stringIndex index of the task as a string.
     * @return a description of the deleted task.
     */
    public String delete(String stringIndex) {
        try {
            int idx = Integer.parseInt(stringIndex) - 1;
            return String.format("I won't remember this task anymore:\n   %s", tasks.remove(idx));
        } catch (NumberFormatException e) {
            return String.format("Hmm, I dunno which task you're talking about. What do you mean by \"%s?\"?",
                    stringIndex);
        }
    }
}
