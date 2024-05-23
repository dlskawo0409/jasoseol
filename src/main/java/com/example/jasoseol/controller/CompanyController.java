package com.example.jasoseol.controller;


import com.example.jasoseol.dto.AddCompanyRequest;
import com.example.jasoseol.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @PostMapping("/api/join/company")
    public ResponseEntity<?> addCompanyProcess(@RequestBody AddCompanyRequest joinDTO){
        boolean joinSuccess = companyService.addCompany(joinDTO);

        if(joinSuccess){
            return ResponseEntity.ok(new JoinController.MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }
    }
}
