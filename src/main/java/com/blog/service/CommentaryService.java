package com.blog.service;

import com.blog.dto.CommentaryDTO;
import com.blog.model.Commentary;
import com.blog.model.Post;
import com.blog.repository.CommentaryRepository;
import com.blog.utils.DateUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CommentaryService {

    private final CommentaryRepository commentaryRepository;

    public CommentaryService(CommentaryRepository commentaryRepository) {
        this.commentaryRepository = commentaryRepository;
    }

    public List<CommentaryDTO> sortCommentariesByDate(Post post) {
        List<Commentary> commentaries = commentaryRepository.findByPostOrderByPublishedDateDesc(post);
        return commentaries.stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    private CommentaryDTO convertToDTO(Commentary commentary) {
        CommentaryDTO dto = new CommentaryDTO();
        dto.setId(commentary.getId());
        dto.setContent(commentary.getContent());
        dto.setPublishedDate(DateUtils.formatPublishedDate(commentary.getPublishedDate()));
        return dto;
    }
}