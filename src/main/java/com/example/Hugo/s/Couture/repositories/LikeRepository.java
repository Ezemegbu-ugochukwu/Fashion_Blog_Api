package com.example.Hugo.s.Couture.repositories;

import com.example.Hugo.s.Couture.model.Like;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
//    List<Like> findAllByPostId(long postId);
//    List<Like> findAllByCommentId(long commentId);
//    Like findByUserAndPostId(String user, Long postId);
//    Like findByUserAndCommentId(String user, Long commentId);
}
