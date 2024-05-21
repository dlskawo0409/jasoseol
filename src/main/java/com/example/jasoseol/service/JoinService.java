package com.example.jasoseol.service;

import com.example.jasoseol.domain.CompanyUser;
import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.AddCompanyUserRequest;
import com.example.jasoseol.dto.AddUserRequest;
import com.example.jasoseol.repository.CompanyUserRepository;
import com.example.jasoseol.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class JoinService {

    private final UserRepository userRepository;
    private final CompanyUserRepository companyUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public JoinService(UserRepository userRepository,CompanyUserRepository companyUserRepository, BCryptPasswordEncoder bCryptPasswordEncoder ) {
        this.userRepository = userRepository;
        this.companyUserRepository = companyUserRepository;
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

//        AddUserRequest data = new AddUserRequest();

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

    public boolean joinProcess(AddCompanyUserRequest joinDTO){
        String email = joinDTO.getEmail();
        String password = joinDTO.getPassword();
        String nickName = joinDTO.getNickname();
        Boolean isExist = userRepository.existsByEmail(email);

        if(isExist){
            return false;
        }

//        AddCompanyUserRequest data = new AddCompanyUserRequest();

        userRepository.save(User.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickName)
                .marketing(joinDTO.getMarketing())
                .build());

        companyUserRepository.save(CompanyUser.builder()
                        .company_num(joinDTO.getCompanyNum())
                        .company_user_name(joinDTO.getCompanyUserName())
                        .company_user_phonenum(joinDTO.getCompanyUserPhonenum())
                .build());


//        companyUserRepository.save(CompanyUser.builder()
//                        .email(email)
//                        .password(bCryptPasswordEncoder.encode(password))
//                        .nickname(joinDTO.getNickname())
//                        .marketing(joinDTO.getMarketing())
//
//
//                .build()
//        );

        return true;
    }



    public boolean existsByEmail(String email){
        return userRepository.existsByEmail(email);
    }
}