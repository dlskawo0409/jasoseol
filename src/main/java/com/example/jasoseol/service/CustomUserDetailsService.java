package com.example.jasoseol.service;

import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.CustomUserDetails;
import com.example.jasoseol.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        //DB에서 조회
        User userData = userRepository.findByEmail(email);

        if (userData != null) {
            //UserDetails에 담아서 return하면 AutneticationManager가 검증 함

            return new CustomUserDetails(userData);
        }



        throw new UsernameNotFoundException("User not found with email: " + email);
    }

//    @Override
//    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
//        // 예시: DB에서 사용자 및 권한을 조회하는 로직
//        // UserEntity는 사용자 정보를 담고 있는 엔티티 클래스라고 가정
//        User userDate = userRepository.findByEmail(email);
//
//        if(userDate == null){
//            throw new UsernameNotFoundException("User not found with email: " + email);
//        }
//
////        List<GrantedAuthority> authorities = new ArrayList<>();
////        for (RoleEntity role : userDate.getRole()) {
////            authorities.add(new SimpleGrantedAuthority("ROLE_" + role.getName()));
////        }
//
//        return new User(userEntity.getUsername(), userEntity.getPassword(), authorities);
//    }

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