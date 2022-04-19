package com.example.Hugo.s.Couture.services;

import org.springframework.stereotype.Service;

@Service
public interface LikeServices {
    long likePost (long uid, long pid);
    long likeComment (long uid, long cid);
    long getAllCommentLikes (long cid);
    long getAllPostLikes(long pid);
}
