package com.library.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.*;

@Component
public class ApplicationEngine {
    private final FailSafePersistentStorage persistentStorage;

    @Autowired
    public ApplicationEngine(FailSafePersistentStorage persistentStorage) {
        this.persistentStorage = persistentStorage;
    }

    public void run() {
        Map<String, Object> filledData = new HashMap<>();
        if(persistentStorage == null) {
            filledData = fillDumpDate();
        }
        filledData.forEach((key, value) -> System.out.println(key + " " + value.toString()));
    }

    public Map<String, Object> fillDumpDate() {
        Map<String, Object> persistentMap = new HashMap<>();
        persistentMap.put("keyOne", new Book(1, "Foundation Trilogy"));
        persistentMap.put("keyTwo", new Book(2, "The Road"));
        persistentMap.put("keyThree", new Book(3, "World War Z: An Oral History of the Zombie War"));
        persistentMap.put("keyFour", new Book(4, "Deep Wheel Orcadia: A Novel"));
        persistentMap.put("keyFive", new Book(5 ,"A River Called Time"));
        persistentMap.put("keyNull", new Book(0, "SNull"));
        return persistentMap;
    }
}
