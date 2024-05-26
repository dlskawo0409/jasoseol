package com.example.jasoseol.service;
import java.io.IOException;

import com.example.jasoseol.uploadfiles.storage.StorageProperties;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.jasoseol.domain.Annoucement;
import com.example.jasoseol.domain.Image;
import com.example.jasoseol.repository.AnnoucementRepository;
import com.example.jasoseol.repository.ImageRepository;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@Service

public class ImageService extends  FileSystemStorageService {


    private final ImageRepository imageRepository;
    private final AnnoucementRepository annoucementRepository;


    public ImageService( StorageProperties properties, ImageRepository imageRepository, AnnoucementRepository annoucementRepository) {
        super(properties);
        this.imageRepository = imageRepository;
        this.annoucementRepository = annoucementRepository;

    }



    @Transactional
    public boolean uploadImage(MultipartFile file, long annoucementID , int usable, int main) throws IOException {
        if (file.isEmpty()) {
            throw new IllegalArgumentException("File is empty");
        }

        // Generate a unique file name
        String fileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String folder = "/image/mainImages/";
        String fileDownloadUri = folder + fileName;
//        Files.copy(file.getInputStream(), Paths.get(rootLocation.toUri()).resolve(fileName));




        Annoucement annoucement = annoucementRepository.findByAnnoucementId(annoucementID);
        if(annoucement == null){
            return false;
        }
        store(file, folder, fileName);



        // Save image information in the database
        Image image = new Image();
        image.setImageUrl(fileDownloadUri);
        image.setAnnoucement(annoucement);
        image.setUsable(usable);
        image.setMain(main);
        imageRepository.save(image);
        return true;
    }
}
