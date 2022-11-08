package com.project.demo.service;

import com.project.dto.UserDto;
import com.project.repository.UserRepository;
import com.project.service.UserServiceImpl;
import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import org.springframework.security.crypto.password.PasswordEncoder;


class UserServiceImplTest {

    private UserServiceImpl userService;
    private PasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        passwordEncoder = Mockito.mock(PasswordEncoder.class);
        userRepository = Mockito.mock(UserRepository.class);

        userService = new UserServiceImpl(userRepository, passwordEncoder);
    }

    @Test
    void checkSave() {
        UserDto userDto = new UserDto();
        userDto.setUsername("user");
        userDto.setEmail("email");
        userDto.setPassword("password");
        userDto.setMatchingPassword("password");

        Mockito.when(passwordEncoder.encode(Mockito.anyString())).thenReturn("password");

        boolean result = userService.register(userDto);

        Assertions.assertTrue(result);
        Mockito.verify(passwordEncoder).encode(Mockito.anyString());
        Mockito.verify(userRepository).save(Mockito.any());
    }
}