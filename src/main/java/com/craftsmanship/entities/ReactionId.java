package com.craftsmanship.entities;

import lombok.Data;
import org.springframework.data.mongodb.core.index.Indexed;

@Data
public class ReactionId {

    private String userId;
    @Indexed
    private String commentId;
}
