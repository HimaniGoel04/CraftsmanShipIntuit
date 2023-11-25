package com.craftsmanship.controllers;

import com.craftsmanship.entities.ReactionId;
import com.craftsmanship.entities.Users;
import com.craftsmanship.enums.ReactionType;
import com.craftsmanship.models.ReactionRequest;
import com.craftsmanship.services.ReactionService;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/reactions")
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping
    public ResponseEntity<List<Users>> getUsersReactedOnComment(@RequestParam String commentId, @RequestParam ReactionType reactionType) {
        List<Users> usersReactingOnComment = reactionService.getUsersReactingOnComment(commentId, reactionType);
        return new ResponseEntity<>(usersReactingOnComment, HttpStatusCode.valueOf(200));
    }

    @PostMapping
    public ResponseEntity<Void> addReaction(@RequestBody ReactionRequest reaction) {
        reactionService.editReaction(reaction);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping
    public ResponseEntity<Void> deleteReaction(@RequestBody ReactionId reactionId) {
        reactionService.deleteReaction(reactionId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
