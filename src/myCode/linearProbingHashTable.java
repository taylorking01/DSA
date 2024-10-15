package myCode;

public class linearProbingHashTable {
    private static final int SIZE = 10;  // Size of the table
    private final int[] keys;  // Array to store keys
    private final int[] values;  // Array to store values
    private final int EMPTY = -1;  // Marker for empty slot

    public linearProbingHashTable() {
        keys = new int[SIZE];
        values = new int[SIZE];
        // Initialize all keys as EMPTY
        for (int i = 0; i < SIZE; i++) {
            keys[i] = EMPTY;
        }
    }

    // Simple hash function
    private int hash(int key) {
        return key % SIZE;
    }

    // Insert operation using linear probing
    public void insert(int key, int value) {
        int index = hash(key);  // Get hash index

        // Linear probing: Find the next available slot
        while (keys[index] != EMPTY) {
            index = (index + 1) % SIZE;  // Move to next slot (circular array)
        }

        // Insert key and value into the found slot
        keys[index] = key;
        values[index] = value;
    }

    // Remove operation using linear probing
    public void remove(int key) {
        int index = hash(key);  // Get hash index

        // Linear probing to find the key to remove
        while (keys[index] != EMPTY) {
            if (keys[index] == key) {
                // Key found, mark as removed
                keys[index] = EMPTY;
                values[index] = EMPTY;
                rehash(index);  // Rehash subsequent elements in the cluster
                return;
            }
            index = (index + 1) % SIZE;  // Continue probing
        }

        System.out.println("Key " + key + " not found.");
    }

    // Rehash all keys in the cluster after a removal
    private void rehash(int start) {
        int index = (start + 1) % SIZE;  // Start rehashing from the next element

        while (keys[index] != EMPTY) {
            int keyToRehash = keys[index];
            int valueToRehash = values[index];
            keys[index] = EMPTY;  // Remove from the current slot
            values[index] = EMPTY;

            insert(keyToRehash, valueToRehash);  // Reinsert the rehashed element
            index = (index + 1) % SIZE;  // Continue to the next element in the cluster
        }
    }

    // Display the hash table
    public void display() {
        System.out.println("Hash Table (Linear Probing):");
        for (int i = 0; i < SIZE; i++) {
            if (keys[i] != EMPTY) {
                System.out.println("Slot " + i + ": " + "Key = " + keys[i] + ", Value = " + values[i]);
            } else {
                System.out.println("Slot " + i + ": empty");
            }
        }
    }

    public static void main(String[] args) {
        linearProbingHashTable table = new linearProbingHashTable();

        // Insert the keys
        int[] keysToInsert = {74, 43, 93, 18, 82, 38, 92};
        for (int key : keysToInsert) {
            table.insert(key, key);  // Using key as value for simplicity
        }

        // Display the hash table before removal
        System.out.println("Before removal:");
        table.display();

        // Remove a key
        table.remove(43);

        // Display the hash table after removal
        System.out.println("After removal of 93:");
        table.display();
    }
}
