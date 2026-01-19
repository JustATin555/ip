/**
 * A store for the items in the chatbot's list
 *
 * @author JustATin555
 */
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
        String[] labelled = new String[size];

        for (int i = 0; i < size; i++) {
            labelled[i] = String.format("%d. %s", i + 1, items[i]);
        }

        return String.join("\n", labelled);
    }
}
