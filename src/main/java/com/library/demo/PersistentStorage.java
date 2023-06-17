package com.library.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Service
public class PersistentStorage {

    String PATH = "C:\\Users\\User\\IdeaProjects\\demo\\src\\main\\resources\\data.dat";
    public File savedHashMapsData = new File(PATH);
    public Map<String, Object> map;

    @PostConstruct
    public void loadData() {
        ApplicationEngine applicationEngine = new ApplicationEngine();
        applicationEngine.testMethod();
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(savedHashMapsData));
            Object readMap = ois.readObject();
            if (readMap instanceof HashMap) {
                map.putAll((HashMap) readMap);
            }
            ois.close();
            System.out.println("Data has been loaded from memory");
        } catch (Exception e) {
            System.out.println("Data could not be loaded");
        }
    }

    @PreDestroy
    public void saveData() {
        try {
            ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(savedHashMapsData));
            objectStream.writeObject(map);
            objectStream.close();
            System.out.println("Data has been saved in persistent format");
        } catch (Exception e) {
            System.out.println("Data could not be saved");
        }
    }

    public PersistentStorage() {
        map = new HashMap<>();
    }

    public void put(String key, Object value) {
        map.put(key, value);
    }

    public Object get(String key) {
        return map.get(key);
    }

    public boolean contains(String key) {
        return map.containsKey(key);
    }

    public boolean remove(String key) {
        if (map.containsKey(key)) {
            map.remove(key);
            return true;
        }
        return false;
    }
}

