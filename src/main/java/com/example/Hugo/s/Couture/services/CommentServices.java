package com.example.Hugo.s.Couture.services;

import com.example.Hugo.s.Couture.dto.CommentDto;
import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.model.Comment;

import java.util.List;

public interface CommentServices {
    Comment newComment(long uid, long pid, CommentDto commentDto);
    Comment editComment(long cid, long uid, CommentDto commentDto);
    List<Comment> listOfPostComments(long pid);
    Comment deleteComment(long cid, long uid);

}
