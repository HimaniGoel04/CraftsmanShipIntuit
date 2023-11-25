package com.craftsmanship.repositories;

import com.craftsmanship.entities.ReactionId;
import com.craftsmanship.entities.Reactions;
import com.craftsmanship.enums.ReactionType;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ReactionRepository extends MongoRepository<Reactions, ReactionId> {

    List<Reactions> findAllByReactionIdCommentIdAndType(String reactionIdCommentId, ReactionType type);

    void deleteByReactionIdCommentId(String commentId);
}
