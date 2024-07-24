package com.blog.repository;

import com.blog.model.Commentary;
import com.blog.model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CommentaryRepository extends JpaRepository<Commentary, Long> {
    List<Commentary> findByPostOrderByPublishedDateDesc(Post post);
}