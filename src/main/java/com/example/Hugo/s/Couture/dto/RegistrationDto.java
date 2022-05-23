package com.example.Hugo.s.Couture.dto;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegistrationDto {
    private String username;
    private String email;
    private String role;
    private String password;
}
