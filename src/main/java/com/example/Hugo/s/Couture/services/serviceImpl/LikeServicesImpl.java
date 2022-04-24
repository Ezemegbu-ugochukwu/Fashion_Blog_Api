package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.Comment;
import com.example.Hugo.s.Couture.model.Like;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.CommentRepository;
import com.example.Hugo.s.Couture.repositories.LikeRepository;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public void likePost(long uid, long pid) {
        Like like = new Like();
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        like.setUser(user);
        likeRepository.save(like);
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("Invalid id"));
        post.getLikes().add(like);
        postRepository.save(post);

    }

    @Override
    public String unlikePost(long uid, long pid) {
        //get the user with id
        //get the like with user
        //get post with id
        // check the list of post likes to find any one that matches the existing like
        // remove it
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        Like like = likeRepository.findByUser(user);
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("Invalid user"));
        List<Like> postLikes = post.getLikes();
        for (int i = 0; i < postLikes.size(); i++) {
            if (postLikes.get(i) == like){
                postLikes.remove(i);

            }
        }
        postRepository.save(post);


//          Like like = likeRepository.findLikeByUserIdAndPostId(uid,pid);
//          likeRepository.delete(like);

          return "Unlike Successful";
    }

//    @Override
//    public void likeComment(long uid, long cid) {
//        Like like = new Like();
//        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
//        like.setUser(user);
//        likeRepository.save(like);
//        Comment comment = commentRepository.findById(cid).orElseThrow(() -> new InvalidUserException("Invalid id"));
//        comment.getLikes().add(like);
//        commentRepository.save(comment);
//    }

//    @Override
//    public void unlikeComment(long uid, long cid) {
//        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
//
//
//    }
//
//    @Override
//    public int getAllCommentLikes(long cid) {
//        Comment comment = commentRepository.findById(cid).orElseThrow(() -> new InvalidUserException("User not found"));
//        return comment.getLikes().size();
//    }

    @Override
    public int getAllPostLikes(long pid) {
        Post post = postRepository.findById(pid).orElseThrow(() -> new InvalidUserException("User not found"));
        return post.getLikes().size();
    }


}
