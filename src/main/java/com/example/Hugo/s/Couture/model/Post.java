package com.example.Hugo.s.Couture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.sql.Time;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long postId;

    @NotBlank
    private String productName;
    private double price;
    private String content;
    private String category;
    private LocalDateTime datePosted;
//    private Time timePosted;

    @JsonIgnore
    @NotNull
    @ManyToOne
    private User user;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Like> likes = new ArrayList<>();

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Comment> comments = new ArrayList<>();

//    private int noOfComments = this.getComments().size();
//    private int noOfLikes = this.getLikes().size();

}
