package com.projects.blog_app.service;

import com.projects.blog_app.model.Post;
import com.projects.blog_app.repo.PostRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostService {

    @Autowired
    private PostRepo repo;

    public Post createPost(Post post){
        System.out.println("Creating new post");
        return repo.save(post);
    }

    public Post getPostById(int id) {
        return repo.findById(id).orElse(new Post());
    }

    public List<Post> getPosts() {
        return repo.findAll();
    }

    public int updatePost(Post post, int id) {
        if(repo.findById(id).isPresent()) {
            Post newPost = new Post();
            newPost.setId(id);
            newPost.setTitle(post.getTitle());
            newPost.setDescription(post.getDescription());
            repo.save(newPost);
            return id;
        }
        return -1;
    }

    public int deletePost(int id) {
        if(repo.findById(id).isPresent()) {
            repo.deleteById(id);
            return id;
        }

        return -1;
    }
}
