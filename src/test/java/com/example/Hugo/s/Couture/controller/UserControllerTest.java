package com.example.Hugo.s.Couture.controller;

import com.example.Hugo.s.Couture.dto.RegistrationDto;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.services.UserServices;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.* ;

@WebMvcTest
public class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserServices userServices;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void givenUserObject_whenCreateUser_thenReturnSavedUser() throws Exception {
     // given-  precondition or setup
        RegistrationDto registrationDto = RegistrationDto.builder()
                .username("mark")
                .email("mark@gmail.com")
                .role("admin")
                .password("12345")
                .build();
        BDDMockito.given(userServices.registerNewUser(ArgumentMatchers.any(RegistrationDto.class))).willAnswer((invocation) -> invocation.getArgument(0));

        // when - action or behaviour that we are going to test
       ResultActions response = mockMvc.perform(post("/users/register").contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(registrationDto)));
        // then - verify the result or output using assert statements
       response.andDo(MockMvcResultHandlers.print()).
               andExpect(MockMvcResultMatchers.status().isCreated()).andExpect(MockMvcResultMatchers.jsonPath("$.username",
               CoreMatchers.is(registrationDto.getUsername()))).andExpect(MockMvcResultMatchers.jsonPath("$.email",
               CoreMatchers.is(registrationDto.getEmail()))).andExpect(MockMvcResultMatchers.jsonPath("$.role",
               CoreMatchers.is(registrationDto.getRole()))).andExpect(MockMvcResultMatchers.jsonPath("$.password",
               CoreMatchers.is(registrationDto.getPassword())));
    }
//    @Test
//    public void givenListOfUsers_whenGetAllUsers_thenReturnListOfUsers() throws Exception{
//        //given - preconditon or setup
//        List<User> listOfUsers = new ArrayList<>();
//        listOfUsers.add(User.builder().username("mike").email("mike@gmail.com").role("admin").password("12345").build());
//        listOfUsers.add(User.builder().username("orobo").email("orobo@gmail.com").role("admin").password("12345").build());
//        BDDMockito.given(userServices.getAllUsers()).willReturn(listOfUsers);
//
//        //when
//        ResultActions response = mockMvc.perform(get("/users/all"));
//        //then
//        response.andExpect(MockMvcResultMatchers.status().isOk())
//                .andDo(MockMvcResultHandlers.print()).andExpect(MockMvcResultMatchers.jsonPath("$.size()",
//                        CoreMatchers.is(listOfUsers.size())));
//    }
}
