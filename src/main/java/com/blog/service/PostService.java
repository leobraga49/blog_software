package com.blog.service;

import com.blog.model.Post;
import com.blog.repository.PostRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class PostService {

    private final PostRepository postRepository;

    public PostService(PostRepository postRepository) {
        this.postRepository = postRepository;
    }

    public List<Post> getAllPosts() {
        return postRepository.findAll();
    }

    public void savePost(Post post) {
        postRepository.save(post);
    }

    public Post getPostById(Long id) {
        Optional<Post> post = postRepository.findById(id);
        return post.orElse(null);
    }

    public void updatePost(Long id, String title, String summary, String content, LocalDateTime publishDate) {
        Optional<Post> optionalPost = postRepository.findById(id);
        if (optionalPost.isPresent()) {
            Post post = optionalPost.get();
            post.setTitle(title);
            post.setSummary(summary);
            post.setContent(content);
            post.setPublishedDate(publishDate);
            postRepository.save(post);
        }
    }

    public void deletePost(Long id) {
        postRepository.deleteById(id);
    }
}
