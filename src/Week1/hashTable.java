package Week1;

class Pair {
    String key;
    String value;

    Pair(String key, String value) {
        this.key = key;
        this.value = value;
    }
}

public class hashTable {
    private static final int SIZE = 10;  // Size of the table
    private final Pair[] table;  // Array to store key-value pairs

    public hashTable() {
        table = new Pair[SIZE];  // Initialize the array
    }

    // Simple hash function
    private int hash(String key) {
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) {
            hashCode += key.charAt(i);
        }
        return hashCode % SIZE;
    }

    // Insert operation
    public void insert(String key, String value) {
        int index = hash(key);  // Get hash index
        table[index] = new Pair(key, value);  // Store the key-value pair
    }

    // Get operation
    public String get(String key) {
        int index = hash(key);  // Get hash index
        Pair pair = table[index];  // Get the pair at that index

        if (pair != null && pair.key.equals(key)) {
            return pair.value;  // Return the associated value if the key matches
        }
        return null;  // Return null if no match is found
    }

    // Remove operation
    public void remove(String key) {
        int index = hash(key);  // Get hash index
        Pair pair = table[index];  // Get the pair at that index

        // If the pair exists and the key matches, remove it by setting the array element to null
        if (pair != null && pair.key.equals(key)) {
            table[index] = null;  // Remove the pair
            System.out.println("Key " + key + " removed.");
        } else {
            System.out.println("Key " + key + " not found.");
        }
    }

    public static void main(String[] args) {
        hashTable map = new hashTable();
        map.insert("name", "A");
        map.insert("city", "Paris");

        System.out.println(map.get("name"));
        System.out.println(map.get("city"));

        // Remove "name"
        map.remove("name");

        // Try getting the removed key
        System.out.println(map.get("name"));  // Output: null (because it was removed)

        // Remove non-existing key
        map.remove("country");  // Output: Key country not found.
    }
}

