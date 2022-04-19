package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.repositories.CommentRepository;
import com.example.Hugo.s.Couture.repositories.LikeRepository;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LikeServicesImpl implements LikeServices {
   private final LikeRepository likeRepository;
   private final CommentRepository commentRepository;
   private final PostRepository postRepository;
   private final UserRepository userRepository;

    @Autowired
    public LikeServicesImpl(LikeRepository likeRepository, CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.likeRepository = likeRepository;
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public long likePost(long uid, long pid) {
        return 0;
    }

    @Override
    public long likeComment(long uid, long cid) {
        return 0;
    }

    @Override
    public long getAllCommentLikes(long cid) {
        return 0;
    }

    @Override
    public long getAllPostLikes(long pid) {
        return 0;
    }
}
