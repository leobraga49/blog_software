package com.blog.controller;

import com.blog.model.Commentary;
import com.blog.model.Post;
import com.blog.repository.CommentaryRepository;
import com.blog.repository.PostRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDateTime;

@Controller
@RequestMapping("/posts")
public class CommentaryController {

    private final PostRepository postRepository;
    private final CommentaryRepository commentaryRepository;

    public CommentaryController(PostRepository postRepository, CommentaryRepository commentaryRepository) {
        this.postRepository = postRepository;
        this.commentaryRepository = commentaryRepository;
    }

    @PostMapping("/{id}/comments")
    public String addComment(@PathVariable Long id, @ModelAttribute Commentary commentary) {
        Post post = postRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("Invalid post id: " + id));

        commentary.setPost(post);
        commentary.setPublishedDate(LocalDateTime.now());
        commentaryRepository.save(commentary);

        return "redirect:/posts/" + id;
    }
}