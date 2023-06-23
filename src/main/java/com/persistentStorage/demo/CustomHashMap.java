package com.persistentStorage.demo;

import java.io.Serializable;

public class CustomHashMap<K, V> implements Serializable {
    private Entry<K, V>[] buckets;
    private int capacity;

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
    }

    public boolean contains(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.getNext();
        }

        return false;
    }

    public boolean remove(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];
        Entry<K, V> prev = null;

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                if (prev == null) {
                    buckets[bucketIndex] = entry.getNext();
                } else {
                    prev.setNext(entry.getNext());
                }
                return true;
            }
            prev = entry;
            entry = entry.getNext();
        }

        return false;
    }

    public void put(K key, V value) {
        int bucketIndex = getBucketIndex(key);

        Entry<K, V> entry = new Entry<>(key, value);

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = entry;
        } else {
            Entry<K, V> current = buckets[bucketIndex];
            while (current.getNext() != null) {
                if (current.getKey().equals(key)) {
                    current.setValue(value);
                    return;
                }
                current = current.getNext();
            }
            current.setNext(entry);
        }
    }

    public Object get(K key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K, V> entry = buckets[bucketIndex];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }

        return null;
    }

    private int getBucketIndex(K key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private static class Entry<K, V> implements Serializable {
        private K key;
        private V value;
        private Entry<K, V> next;

        public Entry(K key, V value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public K getKey() {
            return key;
        }

        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }

        public Entry<K, V> getNext() {
            return next;
        }

        public void setNext(Entry<K, V> next) {
            this.next = next;
        }
    }
}

