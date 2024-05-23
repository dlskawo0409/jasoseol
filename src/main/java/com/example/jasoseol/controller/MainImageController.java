//package com.example.jasoseol.controller;
//
//import com.example.jasoseol.domain.Annoucement;
//import com.example.jasoseol.domain.Image;
//import com.example.jasoseol.repository.ImageRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//import org.springframework.web.multipart.MultipartFile;
//
//import java.io.IOException;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.UUID;
//
//@RestController
//@RequestMapping("/api/upload")
//@RequiredArgsConstructor
//public class ImageUploadController {
//
//    @Value("${file.upload-dir}")
//    private String uploadDir;
//
//
//
////    private final ImageRepository imageRepository;
////
////    @PostMapping("/image")
////    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file,
////                                         @RequestParam("startDate") LocalDateTime startDate,
////                                         @RequestParam("endDate") LocalDateTime endDate) {
////        if (file.isEmpty()) {
////            return ResponseEntity.badRequest().body("File is empty");
////        }
////
////        try {
////            // Generate a unique file name
////            String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
////            Files.copy(file.getInputStream(), Paths.get(uploadDir).resolve(fileName));
////
////            String fileDownloadUri = "/images/" + fileName;
////
////            // Save image information in the database
////            Image image = new Image();
////            image.setUrl(fileDownloadUri);
////            image.setStartDate(startDate);
////            image.setEndDate(endDate);
////            image.setActive(LocalDateTime.now().isAfter(startDate) && LocalDateTime.now().isBefore(endDate));
////
////            imageRepository.save(image);
////
////            return ResponseEntity.ok(image);
////        } catch (IOException e) {
////            e.printStackTrace();
////            return ResponseEntity.status(500).body("Could not upload the file");
////        }
////    }
//}
