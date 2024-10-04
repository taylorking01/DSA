package Week1;

import java.util.LinkedList;
import java.util.List;

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
    private final List<Pair>[] table;  // Array to store key-value pairs

    public hashTable() {
        table = new LinkedList[SIZE];  // Initialize the array
        for (int i=0; i < SIZE; i++){
            table[i] = new LinkedList<>(); //Initialise each bucket with an empty list
        }
    }

    // Simple hash function
    private int hash(String key) {
        int hash = 0;
        int R = 31; //Prime number used in hashing
        for (int i = 0; i < key.length(); i++) {
            hash = (R * hash + key.charAt(i)) % SIZE;
        }
        return hash;
    }

    // Insert operation
    public void insert(String key, String value) {
        int index = hash(key);  // Get hash index
        List<Pair> bucket = table[index]; //Get the bucket at index

        //Check if key already exists, update value if it does
        for (Pair thePair : bucket) {
            if (thePair.key.equals(key)) {
                thePair.value = value; //Update existing value
                return;
            }
        }

        //If key does not exist, add new pair to bucket
        bucket.add(new Pair(key, value));
    }

    // Get operation
    public String get(String key) {
        int index = hash(key);  // Get hash index
        List<Pair> bucket = table[index]; //Get the bucket at index

        //Search for the key in the list
        for (Pair thePair : bucket) {
            if (thePair.key.equals(key)) {
                return thePair.value; //Return the associated value if key matches
            }
        }
        return null; //Return null if no match found
    }

    // Remove operation
    public void remove(String key) {
        int index = hash(key);  // Get hash index
        List<Pair> bucket = table[index]; //Get the bucket at index

        for (Pair thePair : bucket) {
            if (thePair.key.equals(key)) {
                bucket.remove(thePair); //Remove the (k, v) pair from list
                return;
            }
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

        //Remove a non-existing key
        map.remove("AAHHH");
    }
}

