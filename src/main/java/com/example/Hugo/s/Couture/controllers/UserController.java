package com.example.Hugo.s.Couture.controllers;

import com.example.Hugo.s.Couture.dto.LoginDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserServices userServices;

    @Autowired
    public UserController(UserServices userServices) {
        this.userServices = userServices;
    }
    @GetMapping("/all")
    @Cacheable(value = "userDetails")
    public ResponseEntity<List<User>>getAllUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping("/register")
    public ResponseEntity<RegistrationDto> registerNewUser(@RequestBody RegistrationDto registrationDto){
        return userServices.registerNewUser(registrationDto);
    }
    @PostMapping("/login")
    public ResponseEntity<User> userLogin(@Valid @RequestBody LoginDto loginDto){
        return userServices.loginUser(loginDto);
    }
    @PutMapping("/edit/{id}")
    public ResponseEntity<RegistrationDto> editUser(@Valid @PathVariable long id, @RequestBody RegistrationDto registrationDto){
        return userServices.editUser(id, registrationDto);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable long id){

        userServices.deleteUser(id);

        return new ResponseEntity<String>("User deleted successfully", HttpStatus.OK);
    }

}
