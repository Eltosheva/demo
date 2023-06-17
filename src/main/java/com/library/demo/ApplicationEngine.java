package com.library.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.Serializable;
import java.util.*;

@Component
public class ApplicationEngine implements Serializable {
    String PATH = "C:\\Users\\User\\IdeaProjects\\demo\\src\\main\\resources\\data.txt";
    File savedHashMaps = new File(PATH);
    @Autowired
    private final PersistentStorage persistentStorage = new PersistentStorage();

    public void testMethod() {
        Map<String, Object> persistentMap = new HashMap<>();
        if(persistentMap.isEmpty()) {
            persistentMap.put("keyOne", new Book(1, "Foundation Trilogy"));
            persistentMap.put("keyTwo", new Book(2, "The Road"));
            persistentMap.put("keyThree", new Book(3, "World War Z: An Oral History of the Zombie War"));
            persistentMap.put("keyFour", new Book(4, "Deep Wheel Orcadia: A Novel"));
            persistentMap.put("keyFive", new Book(5 ,"A River Called Time"));
            persistentMap.put("keyNull", new Book(0, "SNull"));

        }else{
            persistentStorage.loadData(persistentMap);
        }
    }
}
