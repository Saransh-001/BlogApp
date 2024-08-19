package com.projects.blog_app.controller;

import com.projects.blog_app.model.Post;
import com.projects.blog_app.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    PostService postService;

    @PostMapping
    public ResponseEntity<String> createPost(@RequestBody Post post){
        Post response = postService.createPost(post);

        return new ResponseEntity<>("Post created successfully\nId: " + response.getId(), HttpStatus.CREATED);
    }

    @GetMapping("{id}")
    public ResponseEntity<Post> getPost(@PathVariable int id){
        Post response = postService.getPostById(id);

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<List<Post>> getPosts(){
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @PutMapping("{id}")
    public ResponseEntity<String> updatePost(@RequestBody Post post, @PathVariable int id){
        postService.updatePost(post, id);
        return new ResponseEntity<>("Updated Successfully", HttpStatus.OK);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<String> deletePost(@PathVariable int id){
        postService.deletePost(id);
        return new ResponseEntity<>(" Deleted Successfully", HttpStatus.OK);

    }

}
