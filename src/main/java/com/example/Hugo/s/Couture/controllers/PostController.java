package com.example.Hugo.s.Couture.controllers;

import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.model.Post;
import com.example.Hugo.s.Couture.services.PostServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostServices postServices;

    @Autowired
    public PostController(PostServices postServices) {
        this.postServices = postServices;
    }

    @GetMapping("/all")
    public ResponseEntity<List<Post>> getAllPost() {
        return ResponseEntity.ok(postServices.getAllPost());
    }

    @PostMapping("/addNewPost")
    public ResponseEntity<PostDto> addNewPost(@Valid @RequestParam long uid, @RequestBody PostDto postdto){
     return postServices.makeNewPost(uid, postdto);
    }

    @PutMapping("/editPost/{pid}")
    public  ResponseEntity<PostDto> editPost(@Valid @RequestParam long uid, @PathVariable long pid, @RequestBody PostDto postdto){
        return new ResponseEntity<>(postServices.editPost(pid,uid,postdto), HttpStatus.OK);
    }

//    @DeleteMapping("/delete/{pid}")
//    public Post deletePost(@RequestParam long uid, @PathVariable long pid){
//        return postServices.deletePost(pid,uid);
//    }
     @DeleteMapping("/delete/{pid}")
     public ResponseEntity<String> deletePost(@RequestParam long uid, @PathVariable long pid){
         postServices.deletePost(pid,uid);
         return new ResponseEntity<>("Post entity deleted successfully.", HttpStatus.OK);
     }

    @GetMapping("/userPosts")
    public ResponseEntity<List<Post>> getUserPosts(@Valid @RequestParam long uid){
        return ResponseEntity.ok(postServices.getUserPosts(uid));
    }
}
