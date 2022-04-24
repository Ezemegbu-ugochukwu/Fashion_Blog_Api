package com.example.Hugo.s.Couture.controllers;

import com.example.Hugo.s.Couture.dto.LoginDto;
import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.services.UserServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
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
    public List<User> getAllUsers() {
        return userServices.getAllUsers();
    }

    @PostMapping("/register")
    public User registerNewUser(@RequestBody RegistrationDto registrationDto){
        return userServices.registerNewUser(registrationDto);
    }
    @PostMapping("/login")
    public User userLogin(@Valid @RequestBody LoginDto loginDto){
        User user = userServices.loginUser(loginDto);
        if (user == null){
           throw new InvalidUserException("Invalid email");
        }else {
            System.out.println("User Successfully logged in");
        }
        return user;
    }
    @PutMapping("/edit/{id}")
    public User editUser(@Valid @PathVariable long id, @RequestBody RegistrationDto registrationDto){
        return userServices.editUser(id, registrationDto);
    }
    @DeleteMapping("/delete/{id}")
    public void deleteUser(@PathVariable long id){
        userServices.deleteUser(id);
    }

}
