package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class PostServicesImpl implements PostServices {
    private final PostRepository postRepository;
    private final UserRepository userRepository;
    private ModelMapper mapper;

    public PostServicesImpl(PostRepository postRepository, UserRepository userRepository, ModelMapper mapper) {
        this.postRepository = postRepository;
        this.userRepository = userRepository;
        this.mapper = mapper;
    }

    @Transactional
    @Override
    public Post makeNewPost(long uid, PostDto postdto) {
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("user not found"));
        Post post = new Post();
        post.setProductName(postdto.getProductName());
        post.setPrice(postdto.getPrice());
        post.setContent(postdto.getContent());
        post.setCategory(postdto.getCategory());
        post.setDatePosted(LocalDateTime.now());
        post.setUser(user);
//        user.getListOfPosts().add(post);
//        userRepository.save(user);
        postRepository.save(post);
        return post;

    }

    @Override
    public Post editPost(long pid, long uid, PostDto postdto) {
       Post post = postRepository.getById(pid);
       if (post.getUser().getId() == uid){
           post.setProductName(postdto.getProductName());
           post.setPrice(postdto.getPrice());
           post.setContent(postdto.getContent());
           post.setCategory(postdto.getCategory());
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

    @Override
    public List<Post> getUserPosts(long uid) {
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        return user.getListOfPosts();

    }
}
