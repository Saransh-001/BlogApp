package com.projects.blog_app.service;

import com.projects.blog_app.config.CustomUserDetails;
import com.projects.blog_app.model.Comment;
import com.projects.blog_app.model.Post;
import com.projects.blog_app.model.Roles;
import com.projects.blog_app.model.Users;
import com.projects.blog_app.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    @Autowired
    private CommentService commentService;

    public Post createPost(Post post){
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = customUserDetails.getUser();
        post.setUser(user);
        return repo.save(post);
    }

    public Post getPostById(int id) {
        return repo.findById(id).orElse(new Post());
    }

    public List<Post> getPosts() {
        return repo.findAll();
    }

    public void updatePost(Post newPost, int id) {
        Post post = repo.findById(id).orElseThrow();
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = customUserDetails.getUser();

        if(post.getUser().getId() == user.getId()) {
            newPost.setId(post.getId());
            repo.save(newPost);
        }else{
            throw new IllegalStateException("You can only update your own post");
        }

    }

    public void deletePost(int id) {
        Post post = repo.findById(id).orElseThrow();
        CustomUserDetails customUserDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Users user = customUserDetails.getUser();

        if(post.getUser().getId() == user.getId() || user.getRoles().contains(Roles.ADMIN)) {
            commentService.deleteCommentsByPostId(id);
            repo.deleteById(id);
        }else{
            throw new IllegalStateException("You do not have permission to delete this post");
        }
    }
}
