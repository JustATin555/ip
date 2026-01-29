package Dave.data;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class EventTest {

    private static final LocalDateTime DEFAULT_START = LocalDateTime.parse("2026-01-29T08:00:00");
    private static final String DEFAULT_START_STRING = "Jan 29, 2026 @ 08:00";

    private static final LocalDateTime DEFAULT_END = LocalDateTime.parse("2026-01-29T09:00:00");
    private static final String DEFAULT_END_STRING = "Jan 29, 2026 @ 09:00";

    @Test
    public void event_normalInput_displaysCorrectly() {
        Task task = new Event("Dummy description", DEFAULT_START, DEFAULT_END);
        assertEquals(String.format("[E][ ] Dummy description (from: %s | to: %s)", DEFAULT_START_STRING, DEFAULT_END_STRING), task.toString());
    }

    @Test
    public void event_normalInput_storedCorrectly() {
        Task task = new Event("Dummy description", DEFAULT_START, DEFAULT_END);
        assertEquals(String.format("E | 0 | Dummy description | %s | %s", DEFAULT_START, DEFAULT_END), task.toStored());
    }
}
