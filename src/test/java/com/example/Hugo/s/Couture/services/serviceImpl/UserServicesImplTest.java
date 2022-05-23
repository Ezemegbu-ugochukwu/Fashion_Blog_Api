package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.exceptions.InvalidRequestException;
import com.example.Hugo.s.Couture.exceptions.UserRegistrationException;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.willDoNothing;
import static org.mockito.Mockito.*;

@SpringBootTest
class UserServicesImplTest {
    @MockBean
    private UserRepository userRepository;

    @Autowired
    private UserServicesImpl underTest;
    @Autowired
    private ModelMapper mapper;

    private User user;
    private User user1;

    @BeforeEach
    void setUp() {
        underTest = new UserServicesImpl(userRepository, mapper);
        user = User.builder()
                .id(1L)
                .username("love")
                .email("love@gmail.com")
                .role("admin")
                .password("12345")
                .build();


        Mockito.when(userRepository.save(any())).thenReturn(user);
//        Mockito.when(userRepository.save(any())).thenReturn(user1);
    }

    @Test
    void canGetAllUsers() {
        //when
        underTest.getAllUsers();
        //then
        verify(userRepository).findAll();
    }

    @Test
    void CanRegisterNewUser() {
        RegistrationDto registrationDto = mapper.map(user, RegistrationDto.class);
        ResponseEntity<RegistrationDto> regDto = underTest.registerNewUser(registrationDto);
        assertNotNull(regDto.getBody());
        assertEquals("love@gmail.com", regDto.getBody().getEmail());
    }

    @Test
    void shouldThrowRegistrationException() {
        Mockito.when(userRepository.findUserByEmailOrUsername("love@gmail.com", "love")).thenReturn(Optional.of(user));
        RegistrationDto registrationDto = mapper.map(user, RegistrationDto.class);
        assertThrows(UserRegistrationException.class, () -> underTest.registerNewUser(registrationDto), "user already exists");
    }

//    @Test
//    void loginUser() {
//        Mockito.when(userRepository.findByEmail("love@gmail.com")).thenReturn(Optional.of(user));
//        RegistrationDto registrationDto = mapper.map(loginDto, user.class);
//        underTest.loginUser(loginDto.getEmail());
//    }

    @Test
    void CanEditUser() {
        Mockito.when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        RegistrationDto registrationDto = mapper.map(user, RegistrationDto.class);
        ResponseEntity<RegistrationDto> regDto = underTest.editUser(1L, registrationDto);
        assertEquals(user.getEmail(), regDto.getBody().getEmail());

    }

    @Test
    void ThrowErrorWhenTheUserDontExist() {
        Mockito.when(userRepository.findById(2L)).thenReturn(Optional.empty());
        RegistrationDto registrationDto = mapper.map(user, RegistrationDto.class);
//        ResponseEntity<RegistrationDto> regDto = underTest.editUser(2L, registrationDto);
        assertThrows(InvalidRequestException.class, () -> underTest.editUser(2L, registrationDto), "User not found");
    }

    @Test
    void deleteUser() {
        long userId = 1L;
        Mockito.when(userRepository.findById(userId)).thenReturn(Optional.of(user));
//        willDoNothing().given(userRepository).deleteById(userId);
        userRepository.deleteById(userId);
        underTest.deleteUser(userId);
        verify(userRepository, times(1)).deleteById(userId);

    }
}