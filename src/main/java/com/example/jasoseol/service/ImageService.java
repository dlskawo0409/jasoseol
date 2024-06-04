package com.example.jasoseol.service;
import java.io.IOException;

import com.example.jasoseol.domain.Announcement;
import com.example.jasoseol.uploadfiles.storage.StorageProperties;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.jasoseol.domain.Image;
import com.example.jasoseol.repository.AnnouncementRepository;
import com.example.jasoseol.repository.ImageRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service

public class ImageService extends  FileSystemStorageService {


    private final ImageRepository imageRepository;
    private final AnnouncementRepository announcementRepository;


    public ImageService( StorageProperties properties, ImageRepository imageRepository, AnnouncementRepository announcementRepository) {
        super(properties);
        this.imageRepository = imageRepository;
        this.announcementRepository = announcementRepository;

    }



    @Transactional
    public boolean uploadImage(MultipartFile file, long announcementID , int usable, int main) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String folder = "/image/mainImages/";
        String fileDownloadUri = folder + fileName;
//        Files.copy(file.getInputStream(), Paths.get(rootLocation.toUri()).resolve(fileName));




        Announcement announcement = announcementRepository.findByAnnouncementId(announcementID);
        if(announcement == null){
            return false;
        }
        store(file, folder, fileName);



        // Save image information in the database
        Image image = new Image();
        image.setImageUrl(fileDownloadUri);
        image.setAnnouncement(announcement);
        image.setUsable(usable);
        image.setMain(main);
        imageRepository.save(image);
        return true;
    }
}
