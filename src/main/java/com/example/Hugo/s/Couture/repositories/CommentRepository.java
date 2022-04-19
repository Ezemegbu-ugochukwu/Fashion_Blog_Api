package com.example.Hugo.s.Couture.repositories;

import com.example.Hugo.s.Couture.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepository extends JpaRepository<Comment,Long> {
List<Comment> findCommentsByPostId(long postId);
}
