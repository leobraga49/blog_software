package com.blog.controller;

import com.blog.dto.CommentaryDTO;
import com.blog.dto.PostDTO;
import com.blog.service.CommentaryService;
import com.blog.utils.DateUtils;
import com.blog.model.Post;
import com.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class BlogController {

    private final PostService postService;
    private final CommentaryService commentaryService;

    public BlogController(PostService postService, CommentaryService commentaryService) {
        this.postService = postService;
        this.commentaryService = commentaryService;
    }

    @GetMapping("/index")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        List<PostDTO> postDTOs = posts.stream()
                .map(post -> {
            var dto = new PostDTO();
            dto.setId(post.getId());
            dto.setTittle(post.getTitle());
            dto.setSummary(post.getSummary());
            dto.setContent(post.getContent());
            dto.setFormattedPublishedDate(DateUtils.formatPublishedDate(post.getPublishedDate()));
            return dto;
        }).collect(Collectors.toList());
        model.addAttribute("posts", postDTOs);
        return "index";
    }

    @GetMapping("/editor")
    public String editor() {
        return "editor";
    }

    @PostMapping("/editor/post")
    public String savePost(@RequestParam String title,
                           @RequestParam String summary,
                           @RequestParam String content) {
        Post post = new Post(title, summary, content, LocalDateTime.now());
        postService.savePost(post);
        return "redirect:/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "404";
        }
        List<CommentaryDTO> commentaries = commentaryService.sortCommentariesByDate(post);
        model.addAttribute("post", post);
        model.addAttribute("commentaries", commentaries);
        return "post";
    }

    @GetMapping("/editor/post/{id}")
    public String editPost(@PathVariable Long id, Model model) {
        Post post = postService.getPostById(id);
        if (post == null) {
            return "404";
        }
        model.addAttribute("post", post);
        return "editPost";
    }

    @PostMapping("/editor/post/{id}")
    public String updatePost(@PathVariable Long id,
                             @RequestParam String title,
                             @RequestParam String summary,
                             @RequestParam String content) {
        LocalDateTime publishDateTime = LocalDateTime.now();
        postService.updatePost(id, title, summary, content, publishDateTime);
        return "redirect:/posts/" + id;
    }

    @PostMapping("/editor/delete/{id}")
    public String deletePost(@PathVariable Long id) {
        postService.deletePost(id);
        return "redirect:/index";
    }
}
