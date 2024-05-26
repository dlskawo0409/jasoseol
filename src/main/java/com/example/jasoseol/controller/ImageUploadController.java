package com.example.jasoseol.controller;

import com.example.jasoseol.domain.Image;
//import com.example.jasoseol.dto.AddMainImageRequest;
import com.example.jasoseol.service.ImageService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.time.LocalDateTime;

@RestController
@RequestMapping("/api/upload")

public class ImageUploadController {

    private final ImageService imageService;

    @Autowired
    public ImageUploadController(ImageService imageService) {
        this.imageService = imageService;
    }

    @PostMapping("/image")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
                                         @RequestParam("annoucementID")Long annoucementId,
                                         @RequestParam("usable") int usable,
                                         @RequestParam("main") int main) {
        try {
            boolean result = imageService.uploadImage(file,annoucementId, usable, main);
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        } catch (IOException e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Could not upload the file");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
