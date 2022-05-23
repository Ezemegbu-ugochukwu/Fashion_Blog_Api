package com.example.Hugo.s.Couture.services.serviceImpl;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.exceptions.InvalidUserException;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.model.User;
import com.example.Hugo.s.Couture.repositories.PostRepository;
import com.example.Hugo.s.Couture.repositories.UserRepository;
import com.example.Hugo.s.Couture.services.PostServices;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
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
    public ResponseEntity<PostDto> makeNewPost(long uid, PostDto postdto) {
       Optional<User> user = userRepository.findById(uid);
       if (user.isPresent()) {
           Post post = mapToEntity(postdto);
//        post.setProductName(postdto.getProductName());
//        post.setPrice(postdto.getPrice());
//        post.setContent(postdto.getContent());
//        post.setCategory(postdto.getCategory());
           post.setDatePosted(LocalDateTime.now());
           post.setUser(user.get());
//        user.getListOfPosts().add(post);
//        userRepository.save(user);
           Post newPost = postRepository.save(post);

           //convert entity to DTO
           PostDto postResponse = mapToDto(newPost);

           return new ResponseEntity<>(postResponse, HttpStatus.OK);
       }else {
           throw new InvalidUserException("user not found");
       }

    }

    @Override
    public PostDto editPost(long pid, long uid, PostDto postdto) {
       Post post = postRepository.getById(pid);
       if (post.getUser().getId() == uid){
           post = mapToEntity(postdto);
//           post.setProductName(postdto.getProductName());
//           post.setPrice(postdto.getPrice());
//           post.setContent(postdto.getContent());
//           post.setCategory(postdto.getCategory());
           Post updatedPost = postRepository.save(post);
           PostDto updatedEdit = mapToDto(updatedPost);
           return updatedEdit;
       }
       else {
           throw new InvalidUserException("Invalid User");
       }
    }

    @Override
    public List<Post> getAllPost() {
        return postRepository.findAll();
    }

    @Override
    public Post deletePost(long pid, long uid) {
        Post post = postRepository.findById(pid)
                .orElseThrow(()-> new InvalidUserException("Invalid post"));

            if (post.getUser().getId() == uid){
               postRepository.delete(post);
            return post;
        }
        else {
            throw new InvalidUserException("Invalid User");
        }
    }

    @Override
    public List<Post> getUserPosts(long uid) {
        User user = userRepository.findById(uid).orElseThrow(() -> new InvalidUserException("User not found"));
        return user.getListOfPosts();

    }
    //convert entity into DTO
    public PostDto mapToDto(Post post){
        PostDto postdto = mapper.map(post,PostDto.class);
//        PostDto postdto = new PostDto();
//        postdto.setProductName(post.getProductName());
//        postdto.setPrice(post.getPrice());
//        postdto.setContent(post.getContent());
//        postdto.setCategory(post.getCategory());

        return postdto;
    }
    //Convert DTO into entity
    public Post mapToEntity(PostDto postdto){
        Post post = mapper.map(postdto, Post.class);
//        Post post = new Post();
//        post.setProductName(postdto.getProductName());
//        post.setPrice(postdto.getPrice());
//        post.setContent(postdto.getContent());
//        post.setCategory(postdto.getCategory());


        return post;
    }



}
