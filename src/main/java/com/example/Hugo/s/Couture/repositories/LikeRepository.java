package com.example.Hugo.s.Couture.repositories;

import com.example.Hugo.s.Couture.model.Like;
import com.example.Hugo.s.Couture.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LikeRepository extends JpaRepository<Like,Long> {
//    List<Like> findAllByPostId(long postId);
//    List<Like> findAllByCommentId(long commentId);
//    Like findLikeByUserIdAndPostId(Long userId, Long postId);

    Like findByUser(User user);
//    Like findByUserAndCommentId(String user, Long commentId);
}
