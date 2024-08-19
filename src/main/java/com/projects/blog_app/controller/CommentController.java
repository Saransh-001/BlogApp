package com.projects.blog_app.controller;

import com.projects.blog_app.model.Comment;
import com.projects.blog_app.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentController {

    @Autowired
    private CommentService commentService;

    @PostMapping("/posts/{id}/comment")
    public ResponseEntity<String> addComment(@RequestBody Comment comment,@PathVariable int id) {
        commentService.addComment(comment, id);

        return new ResponseEntity<>("Comment added successfully", HttpStatus.CREATED);
    }

    @GetMapping("/comments/{id}")
    public ResponseEntity<Comment> getCommentById(@PathVariable int id) {
        Comment response = commentService.getCommentById(id);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

    @GetMapping("/posts/{postId}/comments")
    public ResponseEntity<List<Comment>> getCommentsByPostId(@PathVariable int postId) {
        List<Comment> response = commentService.getCommentsByPostId(postId);

        return new ResponseEntity<>(response, HttpStatus.FOUND);
    }

//    @PutMapping("/posts/{postId}/comment/{commentId}")
//    public ResponseEntity<String> updateCommentByCommentId(@PathVariable int commentId, @RequestBody Comment comment, @PathVariable int postId) {
//        commentService.updateCommentByCommentId(commentId, comment, postId);
//
//        return new ResponseEntity<>("Comment updated successfully", HttpStatus.OK);
//    }

//    @DeleteMapping("comments/{id}")
//    public ResponseEntity<String> deleteCommentById(@PathVariable int id) {
//        commentService.deleteCommentById(id);
//
//        return new ResponseEntity<>("Comment deleted successfully", HttpStatus.OK);
//    }

}
