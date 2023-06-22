package com.library.demo;

import java.io.Serializable;

public class CustomHashMap<K,V>  implements Serializable {
    private Entry[] buckets;
    private int capacity;

    public CustomHashMap(int capacity) {
        this.capacity = capacity;
        this.buckets = new Entry[capacity];
    }

    public boolean contains(String key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K,V> entry = buckets[bucketIndex];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return true;
            }
            entry = entry.getNext();
        }

        return false;
    }

    public boolean remove(String key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K,V> entry = buckets[bucketIndex];
        Entry<K,V> prev = null;

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

    public void put(String key, Object value) {
        int bucketIndex = getBucketIndex(key);

        Entry<K,V> entry = new Entry<K,V>(key, value);

        if (buckets[bucketIndex] == null) {
            buckets[bucketIndex] = entry;
        } else {
            Entry<K,V> current = buckets[bucketIndex];
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

    public Object get(String key) {
        int bucketIndex = getBucketIndex(key);
        Entry<K,V> entry = buckets[bucketIndex];

        while (entry != null) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
            entry = entry.getNext();
        }

        return null;
    }

    private int getBucketIndex(String key) {
        return Math.abs(key.hashCode() % capacity);
    }

    private static class Entry<K,V> implements Serializable{
        private String key;
        private Object value;
        private Entry<K,V> next;

        public Entry(String key, Object value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }

        public String getKey() {
            return key;
        }

        public Object getValue() {
            return value;
        }

        public void setValue(Object value) {
            this.value = value;
        }

        public Entry<K,V> getNext() {
            return next;
        }

        public void setNext(Entry<K,V> next) {
            this.next = next;
        }
    }
}

