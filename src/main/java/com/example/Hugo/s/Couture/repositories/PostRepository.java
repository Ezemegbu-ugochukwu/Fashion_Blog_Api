package com.example.Hugo.s.Couture.repositories;

import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllPostsByUser(User user);
}
