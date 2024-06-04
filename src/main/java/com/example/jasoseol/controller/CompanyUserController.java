package com.example.jasoseol.controller;

import com.example.jasoseol.dto.AddCompanyUserRequest;
import com.example.jasoseol.dto.user.ChangeMarketingRequest;
import com.example.jasoseol.repository.CompanyUserRepository;
import com.example.jasoseol.service.JoinService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company-user")
public class CompanyUserController {

    private final JoinService joinService;

    public CompanyUserController(JoinService joinService) {
        this.joinService = joinService;
    }

    @PutMapping("/name/{name}")
    public ResponseEntity<?> changeName(@PathVariable String name){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean chageSuccess = joinService.changeCompanyUserNameService(email, name);

        if(chageSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Change Success"));
        }
        else{
            return ResponseEntity.ok("Change Fail");
        }
    }

    @PutMapping("/phonenum/{phonenum}")
    public ResponseEntity<?> changePhoneNum(@PathVariable String phonenum){
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        boolean chageSuccess = joinService.changeCompanyUserPhoneNum(email, phonenum);

        if(chageSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Change Success"));
        }
        else{
            return ResponseEntity.ok("Change Fail");
        }
    }


}
