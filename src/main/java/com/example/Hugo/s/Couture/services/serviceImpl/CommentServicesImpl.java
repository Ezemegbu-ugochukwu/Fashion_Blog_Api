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
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.time.LocalDateTime;
import java.util.List;

@Component
@Service
public class CommentServicesImpl implements CommentServices {
    private final CommentRepository commentRepository;
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    public CommentServicesImpl(CommentRepository commentRepository, PostRepository postRepository, UserRepository userRepository, ModelMapper mapper) {
        this.commentRepository = commentRepository;
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }



    @Override
    public CommentDto newComment(long uid, long pid, CommentDto commentDto) {
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("User not found"));
//       comment.setCommentContent(commentDto.getCommentContent());
        Comment comment = mapToEntity(commentDto);
        comment.setTimePosted(LocalDateTime.now());
        comment.setUser(user);
//        comment.setPost(post);
       Comment newComment = commentRepository.save(comment);
        post.getComments().add(newComment);
        postRepository.save(post);
        CommentDto postComment = mapToDto(newComment);

        return postComment;

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

    private CommentDto mapToDto (Comment comment){
        CommentDto commentDto = mapper.map(comment, CommentDto.class);
//        CommentDto commentDto = new CommentDto();
//        commentDto.setCommentContent(comment.getCommentContent());
        return commentDto;
    }

    private Comment mapToEntity(CommentDto commentDto){
        Comment comment = mapper.map(commentDto, Comment.class);
//        Comment comment = new Comment();
//        comment.setCommentContent(commentDto.getCommentContent());
        return comment;
    }
}
