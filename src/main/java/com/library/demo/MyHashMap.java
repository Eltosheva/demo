package com.library.demo;

public class MyHashMap<K, V> {
    public MyHashMap(Entry<K, V>[] table, int capacity) {
        this.table = table;
        this.capacity = capacity;
    }

    private static int DEFAULT_CAPACITY = 16;

    private Entry<K, V>[] table;
    private int capacity;

    MyHashMap() {
        this(DEFAULT_CAPACITY);
    }

    MyHashMap(int capacity) {
        this.table = new Entry[capacity];
        this.capacity = capacity;
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

    public boolean contains(K key) {

        return false;
    }

    public boolean remove(K key) {
        return false;
    }

    public void display() {
        for (int i = 0; i < capacity; i++) {
            if (table[i] != null) {
                Entry<K, V> entry = table[i];
                while (entry != null) {
                    System.out.print("{" + entry.key + "=" + entry.value + "}" + " ");
                    entry = entry.next;
                }
            }
        }
    }

    private int indexFor(Object key) {
        return key.hashCode() % table.length;
    }

    private int hash(K key) {
        return Math.abs(key.hashCode()) % capacity;
    }

    public boolean isEmpty() {
        return true;
    }
}
