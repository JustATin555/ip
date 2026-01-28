package Dave.data;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TodoTest {
    @Test
    public void todo_normalInput_displaysCorrectly() {
        Task task = new Todo("Dummy description");
        assertEquals("[T][ ] Dummy description", task.toString());
    }

    @Test
    public void todo_normalInput_storedCorrectly() {
        Task task = new Todo("Dummy description");
        assertEquals("T | 0 | Dummy description", task.toStored());
    }
}
