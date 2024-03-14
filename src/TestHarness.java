public class TestHarness {
    public static void main(String[] args) {
        DSAHashTable hashTable = new DSAHashTable();

        // Test cases
        hashTable.put("key1", "value1");
        hashTable.put("key2", "value2");
        hashTable.put("key3", "value3");

        System.out.println("Value for key1: " + hashTable.get("key1"));
        System.out.println("Value for key2: " + hashTable.get("key2"));
        System.out.println("Value for key3: " + hashTable.get("key3"));

        hashTable.remove("key2");

        System.out.println("Value for key2 after removal: " + hashTable.get("key2"));
        System.out.println("Does hash table have key 'key2'?: " + hashTable.hasKey("key2"));
    }
}
