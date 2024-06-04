package com.example.jasoseol.controller;


import com.example.jasoseol.domain.CompanyUser;
import com.example.jasoseol.dto.AddCompanyRequest;
import com.example.jasoseol.dto.CustomUserDetails;
import com.example.jasoseol.repository.CompanyUserRepository;
import com.example.jasoseol.service.CompanyService;
import com.example.jasoseol.service.CustomUserDetailsService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/company")
public class CompanyController {
    private final CompanyService companyService;
    private final CompanyUserRepository companyUserRepository;

    public CompanyController(CompanyService companyService, CustomUserDetailsService customUserDetailsService, CompanyUserRepository companyUserRepository) {
        this.companyService = companyService;
        this.companyUserRepository = companyUserRepository;
    }

    @PostMapping("")
    public ResponseEntity<?> addCompanyProcess(@RequestBody AddCompanyRequest joinDTO){

//        Authentication authentication =SecurityContextHolder.getContext().getAuthentication();
        CompanyUser companyUser = companyUserRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
//        CustomUserDetails userDetails = (CustomUserDetails) authentication.getPrincipal();
//        UserDetails companyUser = customUserDetailsService.loadUserByUsername(userDetails.getUsername());

        boolean joinSuccess = companyService.addCompany(joinDTO, companyUser);


        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }





}
