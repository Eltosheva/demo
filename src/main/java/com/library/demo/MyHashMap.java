package com.library.demo;


public class MyHashMap<K, V> implements PersistentStorage<K, V> {
    public MyHashMap(Entry<K, V>[] table, int capacity) {
        this.table = table;
        this.capacity = capacity;
    }

    private static int DEFAULT_CAPACITY = 6;

    private Entry<K, V>[] table;
    private int capacity;

    MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    MyHashMap(int capacity) {
        this.table = new Entry[capacity];
    }

    static class Entry<K, V> {
        K key;
        V value;
        Entry<K, V> next;

        Entry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        Entry(K key, V value, Entry<K, V> next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    @Override
    public void put(K key, V value) {
        if (key == null) {
            return;
        }

        Entry<K, V> newEntry = new Entry<>(key, value);
        int hash = hash(key);

        if (table[hash] == null) {
            table[hash] = newEntry;

        } else {
            Entry<K, V> current = table[hash];
            Entry<K, V> previous = null;
            while (current != null) {
                if (current.key.equals(key)) {
                    current.value = newEntry.value;
                    return;
                }
                previous = current;
                current = current.next;
            }
            if (previous == null) return;
            else previous.next = newEntry;
        }
    }

    @Override
    public V get(K key) {
        if (key == null) {
            return null;
        }

        int hash = hash(key);

        if (table[hash] == null) {
            return null;
        } else {
            Entry<K, V> current = table[hash];
            while (current != null) {
                if (current.key.equals(key)) {
                    return current.value;
                }
                current = current.next;
            }
        }
        return null;
    }

    @Override
    public boolean contains(K key) {
        return false;
    }

    @Override
    public boolean remove(K key) {

        return false;
    }

    private int indexFor(Object key) {
        return key.hashCode() % table.length;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }
}
