package com.library.demo;

import java.util.Map;

public interface PersistentStorage {
    public void put(String key, Object value);

    public Object get(String key);

    public boolean contains(String key);

    public boolean remove(String key);
}
