package com.example.jasoseol.controller;


import com.example.jasoseol.domain.CompanyUser;
import com.example.jasoseol.dto.AddCompanyRequest;
import com.example.jasoseol.dto.CustomUserDetails;
import com.example.jasoseol.service.CompanyService;
import com.example.jasoseol.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
public class CompanyController {
    private final CompanyService companyService;
    private final CustomUserDetailsService customUserDetailsService;

    public CompanyController(CompanyService companyService, CustomUserDetailsService customUserDetailsService) {
        this.companyService = companyService;
        this.customUserDetailsService = customUserDetailsService;
    }

    @PostMapping("/api/join/company")
    public ResponseEntity<?> addCompanyProcess(@RequestBody AddCompanyRequest joinDTO, Authentication authentication){
        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
        UserDetails companyUser = customUserDetailsService.loadUserByUsername(userDetails.getUsername());

        boolean joinSuccess = companyService.addCompany(joinDTO, (CompanyUser) companyUser);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }

//    @PutMapping("/api/company/companyName")
//    public ResponseEntity<?> chageCompanyName(@RequestParam("companyName")String  companyName){
//        boolean change = companyService.changeCompanyName(companyName);
//        if(change){
//            return ResponseEntity.ok(new JoinController.MessageResponse("Change Success"));
//        }
//        else{
//            return ResponseEntity.ok("Change Fail");
//        }
//
//    }
}
