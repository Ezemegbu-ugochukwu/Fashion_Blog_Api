package com.example.Hugo.s.Couture.services;

import com.example.Hugo.s.Couture.model.Like;
import org.springframework.stereotype.Service;

import java.util.List;


public interface LikeServices {
    void likePost (long uid, long pid);
   String unlikePost(long uid, long pid);
//    void likeComment (long uid, long cid);
//    void unlikeComment (long uid, long cid)
//    int getAllCommentLikes (long cid);
    int getAllPostLikes(long pid);
}
