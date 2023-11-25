package com.craftsmanship.entities;

import com.craftsmanship.enums.ReactionType;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

@Data
@Document
@Sharded(shardKey = {"reactionId"})
public class Reactions {

    @Id
    private ReactionId reactionId;
    private ReactionType type;
}
