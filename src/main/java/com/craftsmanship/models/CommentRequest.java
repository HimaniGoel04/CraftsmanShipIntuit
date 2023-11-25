package com.craftsmanship.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class CommentRequest {

    @NotNull
    @NotEmpty
    private String commentOnId;

    @NotNull
    @NotEmpty
    private String commentText;

    @NotNull
    @NotEmpty
    private String commentBy;
}
