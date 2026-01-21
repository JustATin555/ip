/**
 * The handlers bundled together
 *
 * @author JustATin555
 */
package ui;

import storage.Tasks;

public class Handler {

    private final Tasks tasks;

    /**
     * Construct a new handler instance
     */
    public Handler() {
        this.tasks = new Tasks();
    }

    /**
     * List all stored tasks
     * @return a formatted list of tasks
     */
    public String list() {
        return String.format("I only remember these tasks:\n%s\nMight have forgotten some though...", tasks);
    }

    /**
     * Mark a task done
     * @param stringIndex index of the task as a string
     * @return a description with the marked task
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
     * Mark a task not done
     * @param stringIndex index of the task as a string
     * @return a description with the unmarked task
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
     * Create a todo
     * @param description of the task
     * @return a description of the task
     */
    public String todo(String description) {
        return String.format("Alright, we'll both try to remember this task:\n   %s",
                tasks.store(description));
    }

    /**
     * Create a deadline
     * @param taskInfo the description and deadline of the task
     * @return a description of the task
     */
    public String deadline(String taskInfo) {
        String[] splitArgs = taskInfo.split(" /by ");

        if (splitArgs.length != 2) return "Hmmm, that doesn't seem like a deadline.";

        return String.format("Alright, we'll both try to remember this task:\n   %s",
                tasks.store(splitArgs[0], splitArgs[1]));
    }

    /**
     * Create an event
     * @param taskInfo the description, start and end of the task
     * @return a description of the task
     */
    public String event(String taskInfo) {
        String[] splitArgs = taskInfo.split(" /from ");

        if (splitArgs.length != 2) return "Hmmm, that doesn't seem like an event.";

        String[] splitTimes = splitArgs[1].split(" /to ");

        if (splitTimes.length != 2) return "Hmmm, that doesn't seem like an event.";

        return String.format("Alright, we'll both try to remember this task:\n   %s",
                tasks.store(splitArgs[0], splitTimes[0], splitTimes[1]));
    }

    /**
     * Delete an existing task
     * @param stringIndex index of the task as a string
     * @return a description with the deleted task
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
