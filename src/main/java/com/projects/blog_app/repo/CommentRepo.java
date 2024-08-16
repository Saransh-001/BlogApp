package com.projects.blog_app.repo;

import com.projects.blog_app.model.Comment;
import com.projects.blog_app.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentRepo extends JpaRepository<Comment, Integer> {

    public List<Comment> findByPostId(int postId);

}
