package com.example.jasoseol.controller;

import com.example.jasoseol.domain.AnnouncementDetails;
import com.example.jasoseol.dto.AddAnnoucementRequest;
import com.example.jasoseol.dto.AddAnnouncementDetailsRequest;
import com.example.jasoseol.dto.AddQuestionRequest;
import com.example.jasoseol.service.AnnouncementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementsService announcementsService;

    public AnnouncementController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @PostMapping("")
    public ResponseEntity<?> addCompanyProcess(@RequestBody AddAnnoucementRequest joinDTO){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean joinSuccess = announcementsService.addAnnouncement(email, joinDTO);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }
//    @GetMapping("/api/announcement")

    @PostMapping("/detail")
    public ResponseEntity<?> addDetailProcess(@RequestBody AddAnnouncementDetailsRequest dto){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean joinSuccess = announcementsService.addAnnouncementDetails(email,dto);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }

    @PostMapping("/question")
    public ResponseEntity<?> addQuestionProcess(@RequestBody AddQuestionRequest dto){

        boolean joinSuccess = announcementsService.addQuestion(dto);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }

}
