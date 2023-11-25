package com.craftsmanship.repositories;

import com.craftsmanship.entities.Comments;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface CommentsRepository extends MongoRepository<Comments, String> {
    List<Comments> findByCommentOnId(String commentOnId);
}
