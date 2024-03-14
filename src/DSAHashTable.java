public class DSAHashTable {
    private static final int DEFAULT_INITIAL_CAPACITY = 10;
    private static final double UPPER_LOAD_FACTOR = 0.75;
    private static final double LOWER_LOAD_FACTOR = 0.25;

    private DSAHashEntry[] hashArray;
    private int size;

    public DSAHashTable() {
        hashArray = new DSAHashEntry[DEFAULT_INITIAL_CAPACITY];
        size = 0;
    }

    public void put(String key, Object value) {
        int hash = hash(key);
        int index = find(key);

        if (index == -1) {
            if ((double) (size + 1) / hashArray.length > UPPER_LOAD_FACTOR) {
                resize(hashArray.length * 2);
                hash = hash(key);
            }
            index = hash;
        }

        hashArray[index] = new DSAHashEntry(key, value);
        size++;
    }

    public boolean hasKey(String key) {
        return find(key) != -1;
    }

    public Object get(String key) {
        int index = find(key);
        if (index != -1) {
            return hashArray[index].getValue();
        }
        return null;
    }

    public void remove(String key) {
        int index = find(key);
        if (index != -1) {
            hashArray[index].setState(DSAHashEntry.State.DELETED);
            size--;
            if ((double) size / hashArray.length < LOWER_LOAD_FACTOR && hashArray.length > DEFAULT_INITIAL_CAPACITY) {
                resize(Math.max(DEFAULT_INITIAL_CAPACITY, hashArray.length / 2));
            }
        }
    }

    private int hash(String key) {
        // Simple hash function, you can replace it with other hash functions
        return Math.abs(key.hashCode()) % hashArray.length;
    }

    private int find(String key) {
        int hash = hash(key);
        int initialHash = hash;
        int stepSize = 1;

        while (hashArray[hash] != null && !hashArray[hash].getKey().equals(key)) {
            hash = (initialHash + stepSize) % hashArray.length;
            stepSize++;
            if (hash == initialHash) {
                // Table is full
                return -1;
            }
        }

        if (hashArray[hash] != null && hashArray[hash].getState() == DSAHashEntry.State.ACTIVE) {
            return hash;
        }

        return -1;
    }

    private void resize(int newSize) {
        DSAHashEntry[] oldArray = hashArray;
        hashArray = new DSAHashEntry[newSize];
        size = 0;

        for (DSAHashEntry entry : oldArray) {
            if (entry != null && entry.getState() == DSAHashEntry.State.ACTIVE) {
                put(entry.getKey(), entry.getValue());
            }
        }
    }
}
