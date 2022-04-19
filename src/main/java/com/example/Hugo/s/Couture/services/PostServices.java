package com.example.Hugo.s.Couture.services;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.model.Post;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface PostServices {
    Post makeNewPost(long uid, PostDto postdto);
    Post editPost(long pid, long uid, PostDto postdto);
    List<Post> getAllPost();
    Post deletePost(long pid, long uid);

}
