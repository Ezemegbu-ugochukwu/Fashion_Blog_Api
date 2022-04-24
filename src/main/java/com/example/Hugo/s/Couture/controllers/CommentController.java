package com.example.Hugo.s.Couture.controllers;

import com.example.Hugo.s.Couture.dto.CommentDto;
import com.example.Hugo.s.Couture.dto.PostDto;
import com.example.Hugo.s.Couture.model.Comment;
import com.example.Hugo.s.Couture.services.CommentServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/comments")
public class CommentController {
    private final CommentServices commentServices;

    @Autowired
    public CommentController(CommentServices commentServices) {
        this.commentServices = commentServices;
    }

    @GetMapping("/all/{pid}")
    public ResponseEntity<List<Comment>> getAllComments(@Valid @PathVariable long pid) {
        return ResponseEntity.ok(commentServices.listOfPostComments(pid));
    }
    @PostMapping("/makeNewComment/{pid}")
    public ResponseEntity<Comment> commentOnPost(@Valid @RequestParam long uid, @RequestBody CommentDto commentDto, @PathVariable long pid){
     return new ResponseEntity<>(commentServices.newComment(uid, pid, commentDto), HttpStatus.CREATED);
    }
    @PutMapping("/editComment/{cid}")
    public ResponseEntity<Comment> editComment(@Valid @RequestParam long uid, @RequestBody CommentDto commentDto, @PathVariable long cid){
        return new ResponseEntity<>(commentServices.editComment(cid,uid,commentDto), HttpStatus.OK);
    }
    @DeleteMapping("/deleteComment/{cid}")
    public ResponseEntity<String> deleteComment(@RequestParam long uid, @PathVariable long cid){
         commentServices.deleteComment(cid,uid);
         return new ResponseEntity<>("Comment entity deleted successfully.", HttpStatus.OK);
    }


}
