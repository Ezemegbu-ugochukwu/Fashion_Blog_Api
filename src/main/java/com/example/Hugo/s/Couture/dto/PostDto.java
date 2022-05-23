package com.example.Hugo.s.Couture.dto;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PostDto {
     private String productName;
     private double price;
     private String content;
     private String category;
     private LocalDateTime datePosted;

}
