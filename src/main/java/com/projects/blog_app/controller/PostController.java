package com.projects.blog_app.controller;

import com.projects.blog_app.model.Post;
import com.projects.blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping("/post")
    public ResponseEntity<String> createPost(@RequestBody Post post){
        Post response = postService.createPost(post);

        return new ResponseEntity<>("Post created successfully\nId: " + response.getId(), HttpStatus.CREATED);
    }

    @GetMapping("/post/{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id){
        Post response = postService.getPostById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/posts")
    public ResponseEntity<List<Post>> getPosts(){
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @PutMapping("/post/{id}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable int id){
        int response = -1;
        response = postService.updatePost(post, id);
        if(response != -1){
            return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);
        }

        return new ResponseEntity<>("Post not found", HttpStatus.NOT_FOUND);

    }

    @DeleteMapping("/post/{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id){
        int response = -1;
        response = postService.deletePost(id);
        if(response != -1){
            return new ResponseEntity<>("Id: " + response + " Deleted Successfully", HttpStatus.OK);
        }
        return new ResponseEntity<>("Deletion Failed as Post Not Found", HttpStatus.NOT_FOUND);
    }

}
