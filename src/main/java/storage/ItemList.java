package storage;

public class ItemList {

    private final String[] items = new String[100];
    private int size = 0;

    /**
     * Add a new item to the list
     * @param item to add to the list
     */
    public void add(String item) {
        // Store item
        items[size] = item;

        // Increment size
        size++;
    }
}
