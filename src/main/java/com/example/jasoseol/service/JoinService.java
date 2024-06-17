package com.example.jasoseol.service;

import com.example.jasoseol.domain.CompanyUser;
import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.AddCompanyUserRequest;
import com.example.jasoseol.dto.user.AddUserRequest;
import com.example.jasoseol.dto.user.ChangeMarketingRequest;
import com.example.jasoseol.repository.CompanyUserRepository;
import com.example.jasoseol.repository.UserRepository;
import org.springframework.security.core.context.SecurityContextHolder;
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

        AddUserRequest data = new AddUserRequest();

        userRepository.save(User.builder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname("아무개")
                .marketing(joinDTO.getMarketing())
                .role("USER")

                .build());

        return true;
    }



    public boolean joinProcess(AddCompanyUserRequest joinDTO){
        String email = joinDTO.getEmail();
        String password = joinDTO.getPassword();
        String nickName = joinDTO.getNickname();
        Boolean isExist = userRepository.existsByEmail(email);

        if (isExist) {
            return false;
        }

        // CompanyUser 객체 생성 및 저장
        CompanyUser companyUser = CompanyUser.companyUserBuilder()
                .email(email)
                .password(bCryptPasswordEncoder.encode(password))
                .nickname(nickName)
                .marketing(joinDTO.getMarketing())
                .career(0)
                .role("COMPANY")
                .companyNum(joinDTO.getCompanyNum())
                .companyUserName(joinDTO.getCompanyUserName())
                .companyUserPhonenum(joinDTO.getCompanyUserPhonenum())
                .comePath(joinDTO.getComePath())
                .build();

        companyUserRepository.save(companyUser);

        return true;
    }

    public boolean changeCompanyUserNameService(String email, String afterName){
        CompanyUser companyUser = companyUserRepository.findByEmail(email);
        if(companyUser == null){
            return false;
        }

        companyUser.setCompanyUserName(afterName);
        companyUserRepository.save(companyUser);
        return true;
    }

    public boolean changeCompanyUserPhoneNum(String email, String phoneNum){
        CompanyUser companyUser = companyUserRepository.findByEmail(email);
        if(companyUser == null){
            return false;
        }

        companyUser.setCompanyUserPhonenum(phoneNum);
        companyUserRepository.save(companyUser);
        return true;
    }


    public int existsByEmail(String email){
        User user = userRepository.findByEmail(email);
        if(user == null){
            return 0; // 존재 하지 않음
        }
        else if(user.getRole().equals("USER")){// 일반 유저
            return 1;
        }
        else if(user.getRole().equals("COMPANY")){ // 기업 유저
            return 2;
        }

        return -1; // 실패
    }

    public boolean changeMarketingProcess(int marketing) {
        User user = userRepository.findByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
        if(user == null){
            return false;
        }

        user.setMarketing(marketing);
        userRepository.save(user);
        return true;
    }
}