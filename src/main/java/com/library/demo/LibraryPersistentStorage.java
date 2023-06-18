package com.library.demo;

import org.springframework.stereotype.Component;

@Component
public class LibraryPersistentStorage implements PersistentStorage {

    FailSavePersistentStorage failSavePersistentStorage;

    @Override
    public void put(String key, Object value) {
        failSavePersistentStorage.map.put(key, value);
    }

    @Override
    public Object get(String key) {
        return failSavePersistentStorage.map.get(key);
    }

    @Override
    public boolean contains(String key) {
        return failSavePersistentStorage.map.containsKey(key);
    }

    @Override
    public boolean remove(String key) {
        if (failSavePersistentStorage.map.containsKey(key)) {
            failSavePersistentStorage.map.remove(key);
            return true;
        }
        return false;
    }
}

