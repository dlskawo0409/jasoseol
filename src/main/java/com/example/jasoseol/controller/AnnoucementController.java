package com.example.jasoseol.controller;

import com.example.jasoseol.dto.AddAnnoucementRequest;
import com.example.jasoseol.dto.AddCompanyRequest;
import com.example.jasoseol.service.AnnoucementService;
import com.example.jasoseol.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AnnoucementController {

    private final AnnoucementService annoucementService;

    public AnnoucementController(AnnoucementService annoucementService) {
        this.annoucementService = annoucementService;
    }


    @PostMapping("/api/upload/annoucement")
    public ResponseEntity<?> addCompanyProcess(@RequestBody AddAnnoucementRequest joinDTO){
        boolean joinSuccess = annoucementService.addAnnoucement(joinDTO);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }

}
