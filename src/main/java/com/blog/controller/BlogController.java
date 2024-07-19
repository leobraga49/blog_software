package com.blog.controller;

import com.blog.model.Post;
import com.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@Controller
public class BlogController {

    private final PostService postService;

    public BlogController(PostService postService) {
        this.postService = postService;
    }

    @GetMapping("/index")
    public String home(Model model) {
        List<Post> posts = postService.getAllPosts();
        model.addAttribute("posts", posts);
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
        model.addAttribute("post", post);
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
                             @RequestParam String content,
                             @RequestParam String publishDate) {
        LocalDateTime publishDateTime = LocalDateTime.parse(publishDate);
        postService.updatePost(id, title, summary, content, publishDateTime);
        return "redirect:/posts/" + id;
    }
}
