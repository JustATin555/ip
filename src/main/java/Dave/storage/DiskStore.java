package Dave.storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;
import java.time.LocalDateTime;
import java.util.ArrayList;

import Dave.data.Deadline;
import Dave.data.Event;
import Dave.data.Task;
import Dave.data.Todo;

/**
 * Represents a long-term storage medium.
 * Saves tasks to a file on the disk.
 *
 * @author JustATin555
 * @version 1.0
 */
public class DiskStore {

    /** The location of the storage file */
    private final Path filePath;

    /**
     * Constructs a new disk storage instance.
     * Requires a preset storage file location.
     * Will create new files / folders if they do not exist yet.
     *
     * @param filePath The location of the storage file.
     */
    public DiskStore(Path filePath) {
        this.filePath = filePath;
    }

    /**
     * Parses a given string into a task.
     *
     * @param taskString A string representing the task.
     * @return A parsed task.
     */
    private static Task parseTask(String taskString) throws InvalidTaskParameterException {
        if (taskString.isEmpty()) return new EmptyTask();
        String[] params = taskString.split(" \\| ");

        Task task = switch (params[0]) {
            case "T" -> {
                if (params.length != 3) throw new InvalidTaskParameterException();
                yield new Todo(params[2]);
            }
            case "D" -> {
                if (params.length != 4) throw new InvalidTaskParameterException();
                yield new Deadline(
                        params[2],
                        LocalDateTime.parse(params[3]));
            }
            case "E" -> {
                if (params.length != 5) throw new InvalidTaskParameterException();
                yield new Event(
                        params[2],
                        LocalDateTime.parse(params[3]),
                        LocalDateTime.parse(params[4]));
            }
            default -> throw new InvalidTaskParameterException();
        };

        if (params[1].equals("1")) task.setDone(true);

        return task;
    }

    /**
     * Prints an error message and stops the process.
     */
    private static void errorOut() {
        System.out.println("""
                ____________________________________________________________
                I seem to have lost my notebook!
                ____________________________________________________________""");
        System.exit(404);
    }

    /**
     * Loads existing tasks from storage.
     *
     * @return A list of parsed tasks.
     */
    public ArrayList<Task> load() {

        // TODO: Handle missing parent directories

        ArrayList<Task> tasks = new ArrayList<>();

        try {
            if (Files.exists(filePath)) {
                try {
                    for (String line : Files.readAllLines(filePath)) {
                        Task task = DiskStore.parseTask(line);

                        if (!(task instanceof EmptyTask)) tasks.add(task);
                    }
                } catch (InvalidTaskParameterException exception) {
                    Files.deleteIfExists(filePath);
                    tasks.clear();
                }
            }
        } catch (IOException exception) {
            errorOut();
        }

        return tasks;
    }

    /**
     * Appends a new task to the file
     *
     * @param task The new task.
     */
    public void save(Task task) {
        try {
            Files.writeString(
                    filePath,
                    task.toStored() + "\n",
                    StandardOpenOption.APPEND, StandardOpenOption.CREATE);
        } catch (IOException exception) {
            errorOut();
        }
    }

    /**
     * Overwrite existing storage with a list of tasks
     *
     * @param tasks The new tasks.
     */
    public void overwrite(ArrayList<Task> tasks) {
        try {
            Files.deleteIfExists(filePath);
            Files.writeString(
                    filePath,
                    String.join(
                            "\n",
                            tasks.stream()
                                    .map(Task::toStored)
                                    .toList()),
                    StandardOpenOption.CREATE);
        } catch (IOException exception) {
            errorOut();
        }
    }
}

/**
 * Represents an exception caused by invalid parameters in a storage file.
 */
class InvalidTaskParameterException extends Exception {
}

/**
 * Represents an empty task to be filtered out
 */
class EmptyTask extends Task {
    /**
     * Constructs an empty (placeholder) task.
     */
    public EmptyTask() {
        super("An empty task");
    }

    @Override
    public String toString() {
        return "???";
    }

    @Override
    public String toStored() {
        return "";
    }
}