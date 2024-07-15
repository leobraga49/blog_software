package com.blog.service;

import com.blog.model.Post;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {

    private static final String POSTS_FILE = "posts.json";
    private final ObjectMapper objectMapper;

    public PostService() {
        this.objectMapper = new ObjectMapper();
    }

    public List<Post> getAllPosts() {
        if (new File(POSTS_FILE).exists()) {
            try (FileReader reader = new FileReader(POSTS_FILE)) {
                return objectMapper.readValue(reader, new TypeReference<List<Post>>() {});
            } catch (IOException e) {
                e.printStackTrace();
                return new ArrayList<>();
            }
        } else {
            return new ArrayList<>();
        }
    }

    public void savePost(Post post) {
        List<Post> posts = getAllPosts();
        posts.add(post);
        try (FileWriter writer = new FileWriter(POSTS_FILE)) {
            objectMapper.writeValue(writer, posts);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
