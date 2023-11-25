package com.craftsmanship.models;

import lombok.Data;

@Data
public class EditCommentRequest {
    private String commentId;
    private String newComment;
    private String commentBy;
}
