package com.example.jasoseol.controller;


import com.example.jasoseol.service.ImageService;
import com.example.jasoseol.uploadfiles.storage.StorageProperties;
import lombok.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.MediaType;
import java.io.IOException;
import java.util.List;


@RestController
@RequestMapping("/api/image")
@ConfigurationProperties(prefix = "storage")
public class ImageController {
    private final ImageService imageService;
    private final ResourceLoader resourceLoader;
    private final StorageProperties storageProperties;

    @Autowired
    public ImageController(ImageService imageService, ResourceLoader resourceLoader, StorageProperties storageProperties) {
        this.imageService = imageService;
        this.resourceLoader = resourceLoader;
        this.storageProperties = storageProperties;
    }

    @PostMapping("")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("announcementID")Long annoucementId,
                                         @RequestParam("usable") int usable,
                                         @RequestParam("main") int main,
                                         @RequestParam("imageUrl") String imageUrl){
        try {
            boolean result = imageService.uploadImage(file,annoucementId, usable, main, imageUrl);
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Could not upload the file");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
    @GetMapping("/main")
    public List<String> getMainImage(){
        return imageService.getMainImagesProcess();
    }

    @GetMapping("/{filename}")
    public ResponseEntity<byte[]> getImage(@PathVariable String filename) throws Exception {
        System.out.println(filename);
        Resource resource = resourceLoader.getResource("file:" + storageProperties.getLocation() + storageProperties.getImageFolder() + filename);

        byte[] imageBytes = StreamUtils.copyToByteArray(resource.getInputStream());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
                .contentType(MediaType.IMAGE_JPEG)
                .body(imageBytes);
    }
}
