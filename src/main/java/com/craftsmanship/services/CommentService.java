package com.craftsmanship.services;

import com.craftsmanship.entities.Comments;
import com.craftsmanship.entities.Reactions;
import com.craftsmanship.enums.ReactionType;
import com.craftsmanship.exceptions.AccessDeniedException;
import com.craftsmanship.exceptions.InternalServerErrorException;
import com.craftsmanship.exceptions.ResourceNotFoundException;
import com.craftsmanship.models.CommentRequest;
import com.craftsmanship.models.EditCommentRequest;
import com.craftsmanship.models.ReactionRequest;
import com.craftsmanship.repositories.CommentsRepository;
import com.craftsmanship.repositories.ReactionRepository;
import com.craftsmanship.utils.CommonUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class CommentService {

    CommentsRepository commentsRepository;
    ReactionRepository reactionRepository;

    public CommentService(CommentsRepository commentsRepository, ReactionRepository reactionRepository) {
        this.commentsRepository = commentsRepository;
        this.reactionRepository = reactionRepository;
    }

    public Comments getComment(String commentId) {
        try {
            Optional<Comments> commentsOptional = commentsRepository.findById(commentId);
            if (commentsOptional.isPresent()) {
                return commentsOptional.get();
            }
            throw new ResourceNotFoundException("Couldn't find comment with id: " + commentId);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Something went wrong while fetching comment with id: " + commentId);
        }
    }

    public List<Comments> getCommentsOn(String commentOnId) {
        try {
            return commentsRepository.findByCommentOnId(commentOnId);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Something went wrong while fetching comments on: " + commentOnId);
        }
    }

    public Comments addComment(CommentRequest commentRequest) {
        try {
            Comments comment = CommonUtils.copyObjectProperties(commentRequest, Comments.class);
            if(comment!=null) {
                return commentsRepository.save(comment);
            }
        } catch (Exception exception) {
            throw new InternalServerErrorException("Something went wrong while adding comment: " + commentRequest);
        }
        return null;
    }

    public Comments editComment(EditCommentRequest editCommentRequest) {
        Comments oldComment = getComment(editCommentRequest.getCommentId());
        if (!oldComment.getCommentBy().equals(editCommentRequest.getCommentBy())) {
            throw new AccessDeniedException(editCommentRequest.getCommentBy() + " is not authorized to edit this comment");
        }
        try {
            oldComment.setCommentText(editCommentRequest.getNewComment());
            return commentsRepository.save(oldComment);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Something went wrong while adding comment: " + oldComment);
        }
    }

    public void deleteComment(String commentId) {
        try {
            List<Comments> commentsOn = getCommentsOn(commentId);
            for (Comments comment : commentsOn) {
                deleteComment(comment.getCommentId());
            }
            deleteAllReactionsOnComment(commentId);
            commentsRepository.deleteById(commentId);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Something went wrong while deleting comment: " + commentId);
        }
    }

    public void deleteAllReactionsOnComment(String commentId) {
        try {
            reactionRepository.deleteByReactionIdCommentId(commentId);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to delete reactions on comment: " + commentId);
        }
    }

    public void increaseReactionCount(ReactionRequest reaction) {
        modifyReactionCount(reaction.getReactionId().getCommentId(), 1, reaction.getType());
    }

    public void decreaseReactionCount(ReactionRequest reaction) {
        modifyReactionCount(reaction.getReactionId().getCommentId(), -1, reaction.getType());
    }

    public void decreaseReactionCount(Reactions reaction) {
        modifyReactionCount(reaction.getReactionId().getCommentId(), -1, reaction.getType());
    }

    public void modifyReactionCount(String commentId, int add, ReactionType reactionType) {
        if (reactionType == ReactionType.LIKE) {
            modifyReactionCount(commentId, add, 0);
        } else {
            modifyReactionCount(commentId, 0, add);
        }
    }

    public void modifyReactionCount(String commentId, int addLike, int addDislike) {
        try {
            Comments comment = getComment(commentId);
            comment.setLikesCount(comment.getLikesCount() + addLike);
            comment.setDislikesCount(comment.getDislikesCount() + addDislike);
            commentsRepository.save(comment);
        } catch (Exception e) {
            log.error("Failed to modify count for commentId: {}", commentId);
        }
    }
}
