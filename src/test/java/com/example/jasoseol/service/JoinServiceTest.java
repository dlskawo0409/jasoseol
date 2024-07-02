package com.example.jasoseol.service;

import static org.junit.jupiter.api.Assertions.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import com.example.jasoseol.domain.User;
import com.example.jasoseol.dto.user.AddUserRequest;
import com.example.jasoseol.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class JoinServiceTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @InjectMocks
    private JoinService joinService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("testJoinProcess_UserExists")
    void testJoinProcess_UserExists() {
        AddUserRequest request = new AddUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setMarketing(1);

        when(userRepository.existsByEmail("test@example.com")).thenReturn(true);

        boolean result = joinService.joinProcess(request);

        assertFalse(result);
        verify(userRepository, never()).save(any(User.class));
    }

    @Test
    void testJoinProcess_UserDoesNotExist() {
        AddUserRequest request = new AddUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setMarketing(1);

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");

        boolean result = joinService.joinProcess(request);

        assertTrue(result);
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testJoinProcess_SavedUser() {
        AddUserRequest request = new AddUserRequest();
        request.setEmail("test@example.com");
        request.setPassword("password");
        request.setMarketing(1);

        when(userRepository.existsByEmail("test@example.com")).thenReturn(false);
        when(bCryptPasswordEncoder.encode("password")).thenReturn("encodedPassword");

        joinService.joinProcess(request);

        verify(userRepository, times(1)).save(argThat(user ->
                user.getEmail().equals("test@example.com") &&
                        user.getPassword().equals("encodedPassword") &&
                        user.getNickname().equals("아무개") &&
                        user.getMarketing() == 1 &&
                        user.getRole().equals("USER")
        ));
    }
}
