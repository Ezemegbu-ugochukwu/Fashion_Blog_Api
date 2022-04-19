package com.example.Hugo.s.Couture.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


import javax.persistence.*;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.List;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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
    private List<Post> listOfPosts;

    @OneToMany(cascade = CascadeType.ALL)
    private List<Comment> listOfComments;

}
