package com.example.jasoseol.controller;

//import com.example.jasoseol.domain.Bookmark;
import com.example.jasoseol.dto.user.ChangeMarketingRequest;
import com.example.jasoseol.repository.AnnouncementRepository;
//import com.example.jasoseol.service.BookmarkService;
import com.example.jasoseol.service.BookmarkService;
import com.example.jasoseol.service.JoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {
    private final JoinService joinService;
   private final BookmarkService bookmarkService;

    public UserController(JoinService joinService, BookmarkService bookmarkService) {
        this.joinService = joinService;

//        this.bookmarkService = bookmarkService;
        this.bookmarkService = bookmarkService;
    }

    @PutMapping("/marketing/{marketing}")
    public ResponseEntity<?> changeMarketing(@PathVariable int marketing){
        boolean chageSuccess = joinService.changeMarketingProcess(marketing);
        if(chageSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Change Success"));
        }
        else{
            return ResponseEntity.ok("Change Fail");
        }
    }

    @PostMapping("/bookmark/{id}")
    public ResponseEntity<?> addBookMark(@PathVariable Long id){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean addSuccess = bookmarkService.addBookmarkProcess(email, id);
        if(addSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }


}
