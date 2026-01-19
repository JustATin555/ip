package storage;

public class ItemList {

    private final String[] items = new String[100];
    private int size = 0;

    /**
     * Add a new item to the list
     * @param item to add to the list
     */
    public void add(String item) {
        items[size] = item;
        size++;
    }

    /**
     * Provide a string representation of the list
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < size; i++) {
            sb.append(String.format("%d. %s\n", i + 1, items[i]));
        }

        return sb.toString();
    }
}
