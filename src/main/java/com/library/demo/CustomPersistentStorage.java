package com.library.demo;

import java.util.ArrayList;
import java.util.Map;

import static java.util.Objects.requireNonNull;

public class CustomPersistentStorage implements PersistentStorage {
    private ArrayList<MyEntry<String, Object>>[] table;

    private final int defaultSize = 10;
    private int size = 0;

    private static class MyEntry<String, Object> implements Map.Entry<String, Object> {
        String key;
        Object value;

        public MyEntry(String key, Object value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public String getKey() {
            return this.key;
        }

        @Override
        public Object getValue() {
            return this.value;
        }

        @Override
        public Object setValue(Object value) {
            Object temp = this.value;
            this.value = value;
            return temp;
        }
    }

    private int indexFor(String key) {
        return key.hashCode() % table.length;
    }

    public void MyMap() {
        table = new ArrayList[defaultSize];
        for (int i = 0; i < defaultSize; i++) {
            table[i] = new ArrayList<>();
        }
    }

    @Override
    public boolean contains(String key) {
        requireNonNull(key);

        int index = indexFor(key);

        return table[index].stream()
                .anyMatch((e) -> e.getKey().equals(key));
    }

    @Override
    public Object get(String key) {
        requireNonNull(key);

        int index = indexFor(key);

        for (MyEntry<String, Object> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }
        return null;
    }

    @Override
    public void put(String key, Object value) {
        requireNonNull(key);
        requireNonNull(value);

        int index = indexFor(key);

        for (MyEntry<String, Object> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                entry.setValue(value);
            }
        }
        table[index].add(new MyEntry<>(key, value));
        size++;
    }

    @Override
    public boolean remove(String key) {
        requireNonNull(key);

        int index = indexFor(key);

        for (MyEntry<String, Object> entry : table[index]) {
            if (entry.getKey().equals(key)) {
                table[index].remove(entry);
                return true;
            }
        }
        return false;
    }
}
