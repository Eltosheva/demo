package com.library.demo;

public interface PersistentStorage<K, V> {
    void put(K key, V value);

    V get(K key);

    boolean contains(K key);

    boolean remove(K key);

    boolean isEmpty();
}
