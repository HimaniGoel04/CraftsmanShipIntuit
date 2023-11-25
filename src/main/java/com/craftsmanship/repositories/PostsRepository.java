package com.craftsmanship.repositories;

import com.craftsmanship.entities.Posts;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PostsRepository extends MongoRepository<Posts, String> {
}
