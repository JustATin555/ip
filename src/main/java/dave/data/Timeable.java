package dave.data;

import java.time.LocalDateTime;

/**
 * Provides an interface for a task that has a usable timing.
 *
 * @author JustATin555
 * @version 1.0
 */
public interface Timeable {
    LocalDateTime getTime();
}
