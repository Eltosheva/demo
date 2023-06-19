package com.library.demo;

public class LibraryPersistentStorage implements PersistentStorage<String, Object> {

    FileSavePersistentStorage fileSavePersistentStorage;

    @Override
    public void put(String key, Object value) {
        fileSavePersistentStorage.getMap().put(key, value);
    }

    @Override
    public Object get(String key) {
        return fileSavePersistentStorage.getMap().get(key);
    }

    @Override
    public boolean contains(String key) {
        return fileSavePersistentStorage.getMap().containsKey(key);
    }

    @Override
    public boolean remove(String key) {
        if (fileSavePersistentStorage.getMap().containsKey(key)) {
            fileSavePersistentStorage.getMap().remove(key);
            return true;
        }
        return false;
    }
}

