package com.example.jasoseol.controller;


import com.example.jasoseol.dto.AddUserRequest;
import com.example.jasoseol.service.JoinService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
public class JoinController {

    private final JoinService joinService;
    @Autowired
    private AuthenticationManager authenticationManager;
//    @Autowired
//    private final UserRepository userRepository;

    static class MessageResponse {
        private String message;

        public MessageResponse(String message) {
            this.message = message;
        }

        // Getter
        public String getMessage() {
            return message;
        }
    }

    public JoinController(JoinService joinService) {

        this.joinService = joinService;

    }

    @PostMapping("/api/join")
    public ResponseEntity<?> joinProcess(@RequestBody AddUserRequest joinDTO) {
        boolean joinSuccess = joinService.joinProcess(joinDTO);

        if(joinSuccess){ // 자동 로그인
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            joinDTO.getEmail(),
                            joinDTO.getPassword()
                    )
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);

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
//        if(exists){
//            return "Email exists";
//        }
//        return "Email does not exist";
    }


}