package com.example.Hugo.s.Couture.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;
    private String commentContent;
    private LocalDateTime timePosted;
//    private Time timeCommented;
//
//    @ManyToOne
//    private Post post;

    @ManyToOne
    private User user;

}
