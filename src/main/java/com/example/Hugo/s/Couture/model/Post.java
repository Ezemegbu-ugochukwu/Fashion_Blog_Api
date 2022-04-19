package com.example.Hugo.s.Couture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank
    private String postContent;
//    private Date datePosted;
//    private Time timePosted;

    @JsonIgnore
    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();

    private int noOfComments;

}
