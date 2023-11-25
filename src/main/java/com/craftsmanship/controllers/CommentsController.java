package com.craftsmanship.controllers;

import com.craftsmanship.entities.Comments;
import com.craftsmanship.models.CommentRequest;
import com.craftsmanship.models.EditCommentRequest;
import com.craftsmanship.services.CommentService;
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
    public ResponseEntity<List<Comments>> getCommentsOn(@RequestParam("id") String commentOrPostId) {
        List<Comments> commentsOn = commentService.getCommentsOn(commentOrPostId);
        return new ResponseEntity<>(commentsOn, HttpStatusCode.valueOf(200));
    }

    @GetMapping("/comment")
    public ResponseEntity<Comments> getComment(@RequestParam("id") String commentId) {
        return new ResponseEntity<>(commentService.getComment(commentId), HttpStatusCode.valueOf(200));
    }

    @PostMapping("/comment")
    public ResponseEntity<Comments> addComment(CommentRequest commentRequest) {
        return new ResponseEntity<>(commentService.addComment(commentRequest), HttpStatusCode.valueOf(200));
    }

    @PutMapping("/comment")
    public ResponseEntity<Comments> editComment(EditCommentRequest editCommentRequest) {
        return new ResponseEntity<>(commentService.editComment(editCommentRequest), HttpStatusCode.valueOf(200));
    }

    @DeleteMapping("/comment")
    public ResponseEntity<Void> deleteComment(@RequestParam("id") String commentId) {
        commentService.deleteComment(commentId);
        return new ResponseEntity<>(HttpStatusCode.valueOf(200));
    }
}
