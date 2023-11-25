package com.craftsmanship.models;

import com.craftsmanship.entities.ReactionId;
import com.craftsmanship.enums.ReactionType;
import lombok.Data;

@Data
public class ReactionRequest {
    private ReactionId reactionId;
    private ReactionType type;
}
