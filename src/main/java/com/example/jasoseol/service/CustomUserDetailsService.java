package com.example.jasoseol.service;

import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.CustomUserDetails;
import com.example.jasoseol.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String eamil) throws UsernameNotFoundException {

        //DB에서 조회
        User userData = userRepository.findByEmail(eamil);

        if (userData != null) {

            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함
            return new CustomUserDetails(userData);
        }

        return null;
    }

    public int getUserCarrer(String email) throws UsernameNotFoundException{
        User userData = userRepository.findByEmail(email);
        return userData.getCareer();

    }

    public void updateUserCareer(String email , int career) throws UsernameNotFoundException{
        User userData = userRepository.findByEmail(email);
        userData.setCareer(career);
        userRepository.save(userData);
    }
}