package com.craftsmanship.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class CommentResponse {

    private String commentId;
    private String commentOnId;
    private String commentText;
    private String commentBy;
    private Integer likesCount;
    private Integer dislikesCount;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

}
