package com.persistentStorage.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
public class CustomPersistentStorage implements PersistentStorage<String, Object> {

    private static final String PATH = "C:\\Users\\User\\IdeaProjects\\demo\\src\\main\\resources\\data.dat";
    private final File backupDataFile = new File(PATH);
    private CustomHashMap<String, Object> map = new CustomHashMap<>(1);


    @PostConstruct
    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupDataFile))) {
            Map<String, Object> readMap = (HashMap) ois.readObject();
            if (readMap != null) {
                readMap.forEach(map::put);
            }
            System.out.println("Backup data has been loaded successfully");
        } catch (Exception e) {
            System.out.println("Backup data could not be loaded. Reason: " + e.getMessage());
        }
    }

    @PreDestroy
    public void saveData() {
        try (ObjectOutputStream objectStream = new ObjectOutputStream(new FileOutputStream(backupDataFile))) {
            objectStream.writeObject(map);
            System.out.println("Backup data has been saved in persistent format");
        } catch (Exception e) {
            System.out.println("Backup creation failed. Reason:" + e.getMessage());
        }
    }

    @Override
    public void put(String key, Object value) {
        map.put(key, value);
    }

    @Override
    public Object get(String key) {
        return map.get(key);
    }

    @Override
    public boolean contains(String key) {
        return map.contains(key);
    }

    @Override
    public boolean remove(String key) {
        return map.remove(key);
    }

    @Override
    public boolean isEmpty() {
        return map == null;
    }
}

