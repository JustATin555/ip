package storage;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

import data.Tasks;

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
     * Load existing tasks from storage.
     *
     * @throws IOException If the file cannot be read.
     */
    public void load(Tasks tasks) throws IOException {

        // TODO: Handle missing parent directories

        try {
            if (Files.exists(filePath)) {
                for (String line : Files.readAllLines(filePath)) {
                    String[] params = line.split(" \\| ");

                    int idx = switch (params[0]) {
                        case "T" -> {
                            if (params.length != 3) throw new InvalidTaskParameterException();
                            yield tasks.store(params[2]);
                        }
                        case "D" -> {
                            if (params.length != 4) throw new InvalidTaskParameterException();
                            yield tasks.store(params[2], params[3]);
                        }
                        case "E" -> {
                            if (params.length != 5) throw new InvalidTaskParameterException();
                            yield tasks.store(params[2], params[3], params[4]);
                        }
                        default -> throw new InvalidTaskParameterException();
                    };

                    if (params[1].equals("1")) tasks.setDone(idx, true);
                }
            }
        } catch (InvalidTaskParameterException exception) {
            Files.deleteIfExists(filePath);
        }
    }
}

/**
 * Represents an exception caused by invalid parameters in a storage file.
 */
class InvalidTaskParameterException extends Exception {
};
