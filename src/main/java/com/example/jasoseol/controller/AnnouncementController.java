package com.example.jasoseol.controller;

import com.example.jasoseol.domain.Announcement;
import com.example.jasoseol.dto.AddAnnouncementRequest;
import com.example.jasoseol.dto.AddAnnouncementDetailsRequest;
import com.example.jasoseol.dto.AddQuestionRequest;
import com.example.jasoseol.dto.CompanyNameAndTitleDTO;
import com.example.jasoseol.service.AnnouncementsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/announcement")
public class AnnouncementController {

    private final AnnouncementsService announcementsService;

    public AnnouncementController(AnnouncementsService announcementsService) {
        this.announcementsService = announcementsService;
    }

    @PostMapping("")
    public ResponseEntity<?> addCompanyProcess(@RequestBody AddAnnouncementRequest joinDTO){

        String email = SecurityContextHolder.getContext().getAuthentication().getName();

        boolean joinSuccess = announcementsService.addAnnouncement(email, joinDTO);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }

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

//    @GetMapping("/title")
//    public ResponseEntity<JSONArray> searchAnnouncementProcess(@RequestParam("title") String title) throws JSONException {
//        String decodedString = URLDecoder.decode(title, StandardCharsets.UTF_8);
//        System.out.println(decodedString);
//        System.out.println("정보");
//
//        JSONArray jsonArray =announcementsService.getSearchTextOnToday(decodedString);
//
//        return ResponseEntity.ok(jsonArray);
//    }
    @GetMapping("/text")
    public ResponseEntity<List<CompanyNameAndTitleDTO>> searchAnnouncementProcess(@RequestParam("text") String text) {
        List<CompanyNameAndTitleDTO> announcements = announcementsService.getSearchTextOnToday(text);
        return ResponseEntity.ok(announcements);
    }
}
