package com.blog.controller;

import com.blog.model.Post;
import com.blog.service.PostService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
        Post post = new Post(title, summary, content);
        postService.savePost(post);
        return "redirect:/index";
    }

    @GetMapping("/posts/{id}")
    public String viewPost(@PathVariable int id, Model model) {
        List<Post> posts = postService.getAllPosts();
        if (id < 0 || id >= posts.size()) {
            return "404";
        }
        model.addAttribute("post", posts.get(id));
        return "post";
    }

}


