package com.example.jasoseol.controller;


import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.AddUserRequest;
import com.example.jasoseol.service.CustomUserDetailsService;
import com.example.jasoseol.service.JoinService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class JoinController {

    private final JoinService joinService;
    private final CustomUserDetailsService userService;

    static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }

        public String getMessage() {
            return message;
        }
    }

    public JoinController(JoinService joinService, CustomUserDetailsService userService) {

        this.joinService = joinService;
        this.userService = userService;
    }

    @PostMapping("/api/join")
    public ResponseEntity<?> joinProcess(@RequestBody AddUserRequest joinDTO) {
        boolean joinSuccess = joinService.joinProcess(joinDTO);

        if(joinSuccess){ // 자동 로그인
            return ResponseEntity.ok(new MessageResponse("Join Success"));
        }
        else{
            return ResponseEntity.ok("Join Fail");
        }

    }

    @GetMapping("/api/check-email")
    public  ResponseEntity<?> checkEmail(@RequestParam String email) {
        boolean exists = joinService.existsByEmail(email);
//        System.out.println(exists);
        MessageResponse response = exists ?
                new MessageResponse("Email exists") :
                new MessageResponse("Email does not exist");

        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/check-career")
    public ResponseEntity<?> checkCareer(@RequestParam String email) throws Exception {
        int carrer = userService.getUserCarrer(email);
        MessageResponse response = new MessageResponse(Integer.toString(carrer));

        return ResponseEntity.ok(response);
    }

    @PostMapping("/api/change-career")
    public ResponseEntity<?> changeCarrer(@RequestParam String email, int career) throws Exception{
        userService.updateUserCareer(email, career);
        return new ResponseEntity(HttpStatus.OK);
    }




}