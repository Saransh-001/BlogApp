package com.projects.blog_app.repo;

import com.projects.blog_app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepo extends JpaRepository<Post, Integer> {
    List<Post> findAll();
    List<Post> findByUserId(int userId);
}
