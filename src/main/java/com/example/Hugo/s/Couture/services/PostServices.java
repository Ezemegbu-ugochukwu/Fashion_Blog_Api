package com.example.Hugo.s.Couture.services;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.model.Post;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PostServices {
    ResponseEntity<PostDto> makeNewPost(long uid, PostDto postdto);
    PostDto editPost(long pid, long uid, PostDto postdto);
    List<Post> getAllPost();
    Post deletePost(long pid, long uid);
    List<Post> getUserPosts(long uid);

}
