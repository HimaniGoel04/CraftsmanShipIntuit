package com.craftsmanship.controllers;

import com.craftsmanship.entities.Comments;
import com.craftsmanship.models.CommentRequest;
import com.craftsmanship.models.CommentResponse;
import com.craftsmanship.models.EditCommentRequest;
import com.craftsmanship.services.CommentService;
import com.craftsmanship.utils.CommonUtils;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CommentsController {
    private final CommentService commentService;

    public CommentsController(CommentService commentService) {
        this.commentService = commentService;
    }

    @GetMapping("/comment/on")
    public ResponseEntity<List<Comments>> getCommentsOn(@NotNull @NotEmpty @RequestParam("id") String commentOrPostId) {
        List<Comments> commentsOn = commentService.getCommentsOn(commentOrPostId);
        return new ResponseEntity<>(commentsOn, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/comment")
    public ResponseEntity<CommentResponse> getComment(@NotNull @NotEmpty @RequestParam("id") String commentId) {
        CommentResponse commentResponse = CommonUtils.copyObjectProperties(commentService.getComment(commentId), CommentResponse.class);
        return new ResponseEntity<>(commentResponse, HttpStatusCode.valueOf(200));
    }

    @PostMapping("/comment")
    public ResponseEntity<CommentResponse> addComment(@Valid @RequestBody CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.addComment(commentRequest), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/comment")
    public ResponseEntity<CommentResponse> editComment(@Valid @RequestBody EditCommentRequest editCommentRequest) {
        return new ResponseEntity<>(commentService.editComment(editCommentRequest), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Void> deleteComment(@NotNull @NotEmpty @RequestParam("id") String commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
