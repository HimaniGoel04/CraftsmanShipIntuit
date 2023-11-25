package com.craftsmanship.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditCommentRequest {

    @NotNull
    @NotEmpty
    private String commentId;

    @NotNull
    @NotEmpty
    private String newComment;

    @NotNull
    @NotEmpty
    private String commentBy;
}
