package com.example.jasoseol.service;

import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.AddUserRequest;
import com.example.jasoseol.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {

        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public boolean joinProcess(AddUserRequest joinDTO) {

        String email = joinDTO.getEmail();
        String password = joinDTO.getPassword();

        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {
            System.out.println("exist");
            return false;
        }

        AddUserRequest data = new AddUserRequest();

        userRepository.save(User.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname("아무개")
                .marketing(joinDTO.getMarketing())
//                .created_at()
//                .updated_at(joinDTO.getUpdated_at())
//                .deleted_at(joinDTO.getDeleted_at())

                .build());

        return true;
    }

    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}