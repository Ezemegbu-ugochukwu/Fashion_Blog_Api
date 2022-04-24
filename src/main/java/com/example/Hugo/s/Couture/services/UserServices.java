package com.example.Hugo.s.Couture.services;

import com.example.Hugo.s.Couture.dto.LoginDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserServices {
    List<User> getAllUsers();
    User registerNewUser(RegistrationDto registrationDto);
    User loginUser(LoginDto loginDto);
    User editUser(long id, RegistrationDto registrationDto);
    void deleteUser(long id);
}
