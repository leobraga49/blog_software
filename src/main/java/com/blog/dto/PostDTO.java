package com.blog.dto;

public class PostDTO {
    private Long id;
    private String tittle;
    private String summary;
    private String content;
    private String formattedPublishedDate;

    public PostDTO() {
    }

    public PostDTO(Long id, String tittle, String summary, String content, String formattedPublishedDate) {
        this.id = id;
        this.tittle = tittle;
        this.summary = summary;
        this.content = content;
        this.formattedPublishedDate = formattedPublishedDate;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTittle() {
        return tittle;
    }

    public void setTittle(String tittle) {
        this.tittle = tittle;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getFormattedPublishedDate() {
        return formattedPublishedDate;
    }

    public void setFormattedPublishedDate(String formattedPublishedDate) {
        this.formattedPublishedDate = formattedPublishedDate;
    }
}

