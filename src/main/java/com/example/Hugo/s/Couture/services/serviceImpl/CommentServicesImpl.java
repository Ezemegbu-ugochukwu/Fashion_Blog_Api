package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.CommentDto;
import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.Comment;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.CommentRepository;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.CommentServices;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class CommentServicesImpl implements CommentServices {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public CommentServicesImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }



    @Override
    public Comment newComment(long uid, long pid, CommentDto commentDto) {
        Comment comment = new Comment();
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("User not found"));
        comment.setCommentContent(commentDto.getCommentContent());
        comment.setTimePosted(LocalDateTime.now());
        comment.setUser(user);
//        comment.setPost(post);
        commentRepository.save(comment);
        post.getComments().add(comment);
        postRepository.save(post);
        return comment;

    }

    @Override
    public Comment editComment(long cid, long uid, CommentDto commentDto) {
        Comment comment = commentRepository.findById(cid).orElseThrow(() -> new InvalidUserException("invalid"));
        if (comment.getUser().getId() == uid){
            comment.setCommentContent(commentDto.getCommentContent());
            commentRepository.save(comment);
        }
        return comment;
    }


    @Override
    public List<Comment> listOfPostComments(long pid) {

        Post post = postRepository.findById(pid).get();
//    return commentRepository.findCommentsByPostId(pid);
        return post.getComments();
    };

    @Override
    public Comment deleteComment(long cid, long uid) {
        Comment comment = commentRepository.findById(cid).orElseThrow(() -> new InvalidUserException("User not found"));
        if (comment.getUser().getId() == uid){
//            comment.getPost().setComments(comment.getPost().getComments().remove());
            commentRepository.delete(comment);
        }
        return comment;
    }
}
