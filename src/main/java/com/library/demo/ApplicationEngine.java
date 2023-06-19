package com.library.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class ApplicationEngine {
    private final LibraryPersistentStorage libraryPersistentStorage;
    private final CustomPersistentStorage customPersistentStorage;

    @Autowired
    public ApplicationEngine(LibraryPersistentStorage persistentStorage, CustomPersistentStorage customPersistentStorage) {
        this.libraryPersistentStorage = persistentStorage;
        this.customPersistentStorage = customPersistentStorage;
    }

    public void run() {
        if (libraryPersistentStorage.isEmpty()) {
            fillDumpDate().forEach(libraryPersistentStorage::put);
        }
        libraryPersistentStorage.status();
        if (customPersistentStorage.isEmpty()) {
            fillDumpDate().forEach(customPersistentStorage::put);
        }
    }

    public Map<String, Object> fillDumpDate() {
        Map<String, Object> persistentMap = new HashMap<>();
        persistentMap.put("keyOne", new Book(1, "Foundation Trilogy"));
        persistentMap.put("keyTwo", new Book(2, "The Road"));
        persistentMap.put("keyThree", new Book(3, "World War Z: An Oral History of the Zombie War"));
        persistentMap.put("keyFour", new Book(4, "Deep Wheel Orcadia: A Novel"));
        persistentMap.put("keyFive", new Book(5, "A River Called Time"));
        persistentMap.put("keyNull", new Book(0, "SNull"));
        return persistentMap;
    }
}
