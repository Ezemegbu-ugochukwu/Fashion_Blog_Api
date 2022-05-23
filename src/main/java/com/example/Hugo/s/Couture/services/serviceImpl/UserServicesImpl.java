package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.LoginDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.exceptions.InvalidRequestException;
import com.example.Hugo.s.Couture.exceptions.UserRegistrationException;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.UserServices;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Component
@Service
public class UserServicesImpl implements UserServices {
    private final UserRepository userRepository;
    private ModelMapper mapper;

    @Autowired
    public UserServicesImpl(UserRepository userRepository, ModelMapper mapper) {
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Override
    public ResponseEntity<List<User>> getAllUsers() {
        return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
    }

    @Override
    public ResponseEntity<RegistrationDto> registerNewUser(RegistrationDto registrationDto) {

       Optional<User> selectedUser  = userRepository.findUserByEmailOrUsername(registrationDto.getEmail(), registrationDto.getUsername());
       if (selectedUser.isPresent())
           throw new UserRegistrationException("user already exists");
            User user =  mapToEntity(registrationDto);
           User newUser = userRepository.save(user);
           return new ResponseEntity<>(mapToDto(newUser), HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<User> loginUser(LoginDto loginDto) {
        Optional<User> user = userRepository.findByEmail(loginDto.getEmail());
        if (user.isPresent()){
//        throw new InvalidRequestException("Invalid Email/password");
            return new ResponseEntity<>(user.get(),HttpStatus.ACCEPTED);
        }else {
            throw new InvalidRequestException("Invalid Email/password");
        }

    }

    @Override
    public ResponseEntity<RegistrationDto> editUser(long id, RegistrationDto registrationDto) {
       Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
//            throw new InvalidRequestException("User not found");

        user.get().setRole(registrationDto.getRole());
        user.get().setUsername(registrationDto.getUsername());
        user.get().setPassword(registrationDto.getPassword());
        user.get().setEmail(registrationDto.getEmail());
            User editUserDetails = userRepository.save(user.get());
            RegistrationDto editDetails = mapToDto(editUserDetails);
            return new ResponseEntity<>(editDetails, HttpStatus.OK);
        }else {
            throw new InvalidRequestException("User not found");
        }

    }

    @Override
    public void deleteUser(long id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()){
            userRepository.delete(user.get());
//            throw new InvalidRequestException("User not found");
        }else {
            throw new InvalidRequestException("User not found");
        }

    }
    public RegistrationDto mapToDto(User user){
        RegistrationDto registrationDto = mapper.map(user, RegistrationDto.class);
        return registrationDto;
    }
    public User mapToEntity(RegistrationDto registrationDto){
        User user = mapper.map(registrationDto, User.class);
        return user;
    }
}
