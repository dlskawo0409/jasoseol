package com.example.jasoseol.uploadfiles.storage;

import lombok.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */

    private String location = System.getProperty("user.dir") + "/src/main/resources";
    private String imageFolder= System.getProperty("storage.imageFolder");

    public String getLocation() {
        return location;
    }
    public String getImageFolder() {
        return imageFolder;
    }
    public void setLocation(String location) {
        this.location = location;
    }
    public void setImageFolder(String imageFolder) {
        this.imageFolder = imageFolder;
    }
}