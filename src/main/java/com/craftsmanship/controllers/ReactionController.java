package com.craftsmanship.controllers;

import com.craftsmanship.entities.ReactionId;
import com.craftsmanship.entities.Users;
import com.craftsmanship.enums.ReactionType;
import com.craftsmanship.models.ReactionRequest;
import com.craftsmanship.services.ReactionService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ReactionController {

    private final ReactionService reactionService;

    public ReactionController(ReactionService reactionService) {
        this.reactionService = reactionService;
    }

    @GetMapping("/reaction/users")
    public ResponseEntity<List<Users>> getUsersReactedOnComment(@NotNull @NotEmpty @RequestParam String commentId,
                                                                @NotNull @NotEmpty @RequestParam ReactionType reactionType) {
        List<Users> usersReactingOnComment = reactionService.getUsersReactingOnComment(commentId, reactionType);
        return new ResponseEntity<>(usersReactingOnComment, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/reaction")
    public ResponseEntity<Void> addReaction(@Valid @RequestBody ReactionRequest reaction) {
        reactionService.editReaction(reaction);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/reaction")
    public ResponseEntity<Void> deleteReaction(@Valid @RequestBody ReactionId reactionId) {
        reactionService.deleteReaction(reactionId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }

}
