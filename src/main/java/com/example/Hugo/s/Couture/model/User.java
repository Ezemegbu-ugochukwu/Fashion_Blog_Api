package com.example.Hugo.s.Couture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.*;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "blog_users")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    @NotNull
    private String username;
    @Column(unique = true)
    @NotNull
    private String email;
    private String role;
    @Size(min = 4, message = "Password should contain at least 4 characters")
    private String password;
    @OneToMany(cascade = CascadeType.ALL)
    private List<Post> listOfPosts = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> listOfComments = new ArrayList<>();

}
