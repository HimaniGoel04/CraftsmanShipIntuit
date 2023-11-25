package com.craftsmanship.entities;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class ReactionId {

    @NotNull
    @NotEmpty
    private String userId;

    @NotNull
    @NotEmpty
    @Indexed
    private String commentId;
}
