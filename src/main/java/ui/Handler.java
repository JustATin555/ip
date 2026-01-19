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
        return String.format("I think you have these tasks:\n%s\nMight have forgotten some though...", tasks);
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
            return String.format("Ooh, I dunno which task you're talking about. What do you mean by \"%s?\"",
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
            return String.format("Ooh, I dunno which task you're talking about. What do you mean by \"%s?\"",
                    stringIndex);
        }
    }

    /**
     * Create a todo task
     * @param description of the task
     * @return a description of the task
     */
    public String todo(String description) {
        return String.format("Alright, we'll both try to remember this task:\n   %s",
                tasks.store(description));
    }
}
