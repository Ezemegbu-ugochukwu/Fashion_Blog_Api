package com.example.Hugo.s.Couture.exceptions;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class ApiException {
    private Date timeStamp;
    private String message;
    private String details;

}
