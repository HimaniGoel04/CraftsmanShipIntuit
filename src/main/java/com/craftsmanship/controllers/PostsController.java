package com.craftsmanship.controllers;

import com.craftsmanship.entities.Posts;
import com.craftsmanship.exceptions.InternalServerErrorException;
import com.craftsmanship.repositories.PostsRepository;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/post")
public class PostsController {
    private final PostsRepository postsRepository;

    public PostsController(PostsRepository postsRepository) {
        this.postsRepository = postsRepository;
    }

    @PostMapping
    public ResponseEntity<Posts> addPost(@NotNull @NotEmpty String userId) {
        try {
            Posts post = new Posts();
            post.setUserId(userId);
            return new ResponseEntity<>(postsRepository.save(post), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to add post: "+exception.getMessage());
        }

    }

    @GetMapping
    public ResponseEntity<List<Posts>> getAllPosts() {
        try {
            return new ResponseEntity<>(postsRepository.findAll(), HttpStatusCode.valueOf(200));
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to add post: "+exception.getMessage());
        }

    }
}
