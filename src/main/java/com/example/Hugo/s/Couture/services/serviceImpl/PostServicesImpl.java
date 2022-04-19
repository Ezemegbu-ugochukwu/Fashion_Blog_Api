package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.PostServices;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Service
public class PostServicesImpl implements PostServices {
    private final PostRepository postRepository;
    private final UserRepository userRepository;

    public PostServicesImpl(PostRepository postRepository, UserRepository userRepository) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Post makeNewPost(long uid, PostDto postdto) {
        User user = userRepository.getById(uid);
        Post post = new Post();
        post.setPostContent(postdto.getPostContent());
        post.setUser(user);
        postRepository.save(post);
        return post;

    }

    @Override
    public Post editPost(long pid, long uid, PostDto postdto) {
       Post post = postRepository.getById(pid);
       if (post.getUser().getId() == uid){
           post.setPostContent(postdto.getPostContent());
           postRepository.save(post);
       }
       else {
           throw new InvalidUserException("Invalid User");
       }
        return post;
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post deletePost(long pid, long uid) {
        Post post = postRepository.getById(pid);
        if (post.getUser().getId() == uid){
            postRepository.delete(post);
        }else {
            throw new InvalidUserException("Invalid User");
        }
        return post;
    }
}
