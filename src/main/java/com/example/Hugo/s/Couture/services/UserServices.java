package com.example.Hugo.s.Couture.services;

import com.example.Hugo.s.Couture.dto.LoginDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface UserServices {
     ResponseEntity<List<User>> getAllUsers();
    ResponseEntity<RegistrationDto> registerNewUser(RegistrationDto registrationDto);
    ResponseEntity<User> loginUser(LoginDto loginDto);
   ResponseEntity<RegistrationDto> editUser(long id, RegistrationDto registrationDto);
    void deleteUser(long id);
}
