package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.LoginDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.exceptions.InvalidRequestException;
import com.example.Hugo.s.Couture.exceptions.UserRegistrationException;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;

    @Autowired
    public UserServicesImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public User registerNewUser(RegistrationDto registrationDto) {
        User user  = userRepository.findUserByEmailOrUsername(registrationDto.getEmail(), registrationDto.getUsername());
        if (user == null){
            user = new User();
            user.setEmail(registrationDto.getEmail());
            user.setUsername(registrationDto.getUsername());
            user.setRole(registrationDto.getRole());
            userRepository.save(user);
        }else {
            throw new UserRegistrationException("User with email "+user.getEmail()+" or User with Username "+user.getUsername()+ " already exists");
        }
        return user;
    }

    @Override
    public User loginUser(LoginDto loginDto) {
        User user = userRepository.findByEmail(loginDto.getEmail());
        if (user == null){
        throw new InvalidRequestException("Invalid Email/password");
        }
        return user;
    }

    @Override
    public User editUser(long id, RegistrationDto registrationDto) {
        User user = userRepository.getById(id);
        if (user == null){
            throw new InvalidRequestException("User not found");
        }
        user.setRole(registrationDto.getRole());
        user.setUsername(registrationDto.getUsername());
        user.setPassword(registrationDto.getPassword());
        user.setEmail(registrationDto.getEmail());
        userRepository.save(user);
        return user;
    }
}
