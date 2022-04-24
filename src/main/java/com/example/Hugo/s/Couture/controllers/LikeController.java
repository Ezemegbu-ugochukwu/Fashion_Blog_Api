package com.example.Hugo.s.Couture.controllers;

import com.example.Hugo.s.Couture.services.LikeServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/likes")
public class LikeController {
    private final LikeServices likeServices;

    @Autowired
    public LikeController(LikeServices likeServices) {
        this.likeServices = likeServices;
    }
    @PostMapping("/like/{pid}")
    public void likePost(@PathVariable long pid, @RequestParam long uid){
      likeServices.likePost(pid, uid);
    }
    @PostMapping("/unlike/{pid}")
    public String unlikePost(@PathVariable long pid, @RequestParam long uid){
        return likeServices.unlikePost(uid,pid);
    }


}
