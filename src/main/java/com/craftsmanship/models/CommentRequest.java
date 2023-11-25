package com.craftsmanship.models;

import lombok.Data;

@Data
public class CommentRequest {

    private String commentOnId;
    private String commentText;
    private String commentBy;
}
