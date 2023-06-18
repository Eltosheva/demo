package com.library.demo;

public interface PersistentStorage {
    public void put(String key, Object value);

    public Object get(String key);

    public boolean contains(String key);

    public boolean remove(String key);
}
