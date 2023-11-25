package com.craftsmanship.models;

import com.craftsmanship.entities.ReactionId;
import com.craftsmanship.enums.ReactionType;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class ReactionRequest {

    @NotNull
    private ReactionId reactionId;

    @NotNull
    private ReactionType type;
}
