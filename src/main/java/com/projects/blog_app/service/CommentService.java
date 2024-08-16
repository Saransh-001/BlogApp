package com.projects.blog_app.service;

import com.projects.blog_app.model.Comment;
import com.projects.blog_app.model.Post;
import com.projects.blog_app.repo.CommentRepo;
import com.projects.blog_app.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private PostRepo postRepo;

    @Autowired
    private CommentRepo commentRepo;

    public Comment addComment(Comment comment, int postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        comment.setPost(post);

        return commentRepo.save(comment);
    }

    public Comment getCommentById(int id) {
        return commentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Comment not found"));
    }

    public List<Comment> getCommentsByPostId(int postId) {
        postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        return commentRepo.findByPostId(postId);
    }

    public void updateCommentByCommentId(int commentId, Comment comment, int postId) {
        Post post = postRepo.findById(postId)
                .orElseThrow(() -> new RuntimeException("Post not found"));

        commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        comment.setId(commentId);
        comment.setPost(post);

        commentRepo.save(comment);
    }

    public void deleteCommentById(int commentId){
        commentRepo.findById(commentId)
                .orElseThrow(() -> new RuntimeException("Comment not found"));

        commentRepo.deleteById(commentId);
    }

}
