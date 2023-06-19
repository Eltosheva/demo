package com.library.demo;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

@Component
@Setter
@Getter
public class FileSavePersistentStorage {

    private static final String PATH = "C:\\Users\\User\\IdeaProjects\\demo\\src\\main\\resources\\data.dat";
    private final File backupDataFile = new File(PATH);
    private Map<String, Object> map = new HashMap<>();

    @PostConstruct
    public void loadData() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(backupDataFile))) {
            Map<String, Object> readMap = (HashMap) ois.readObject();
            if (readMap != null) {
                map.putAll(readMap);
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
}

