package com.example.jasoseol.service;

import com.example.jasoseol.domain.Announcement;
import com.example.jasoseol.domain.Bookmark;
import com.example.jasoseol.domain.User;
import com.example.jasoseol.repository.AnnouncementRepository;
import com.example.jasoseol.repository.BookmarkRepository;
import com.example.jasoseol.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.awt.print.Book;

@Service
public class BookmarkService {

    private final UserRepository userRepository;
    private final AnnouncementRepository announcementRepository;
    private final BookmarkRepository bookmarkRepository;

    public BookmarkService(UserRepository userRepository,AnnouncementRepository announcementRepository, BookmarkRepository bookmarkRepository) {
        this.userRepository = userRepository;
        this.announcementRepository = announcementRepository;
        this.bookmarkRepository = bookmarkRepository;
    }

    public boolean addBookmarkProcess(String email, Long announcementId){
        User user = userRepository.findByEmail(email);
        Announcement announcement = announcementRepository.findByAnnouncementId(announcementId);

        if(user == null || announcement == null){
            return false;
        }

        if(user.getRole() != "User"){
            return false;
        }
//        Bookmark bookmark = bookmarkRepository.findByUserId(user.getId());
//
//        if(bookmark == null){
//            bookmark = Bookmark.builder()
//                    .user(user)
//                    .announcements(announcement)
//                    .build();
//        }
        bookmarkRepository.save(Bookmark.builder()
                .user(user)
                .announcement(announcement)
                .build()
        );


        return true;
    }
}
