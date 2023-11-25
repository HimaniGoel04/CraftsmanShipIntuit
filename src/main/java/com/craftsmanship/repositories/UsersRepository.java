package com.craftsmanship.repositories;

import com.craftsmanship.entities.Users;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface UsersRepository extends MongoRepository<Users, String> {
}
