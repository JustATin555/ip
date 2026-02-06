package dave.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DeadlineTest {

    private static final LocalDateTime DEFAULT_DEADLINE = LocalDateTime.parse("2026-01-29T08:00:00");
    private static final String DEFAULT_DEADLINE_STRING = "Jan 29, 2026 @ 08:00";

    @Test
    public void deadline_normalInput_displaysCorrectly() {
        Task task = new Deadline("Dummy description", DEFAULT_DEADLINE);
        assertEquals(String.format("[D][ ] Dummy description (by: %s)", DEFAULT_DEADLINE_STRING), task.toString());
    }

    @Test
    public void deadline_normalInput_storedCorrectly() {
        Task task = new Deadline("Dummy description", DEFAULT_DEADLINE);
        assertEquals(String.format("D | 0 | Dummy description | %s", DEFAULT_DEADLINE), task.toStored());
    }
}
