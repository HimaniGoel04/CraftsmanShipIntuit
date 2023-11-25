package com.craftsmanship.services;

import com.craftsmanship.entities.ReactionId;
import com.craftsmanship.entities.Reactions;
import com.craftsmanship.entities.Users;
import com.craftsmanship.enums.ReactionType;
import com.craftsmanship.exceptions.InternalServerErrorException;
import com.craftsmanship.models.ReactionRequest;
import com.craftsmanship.repositories.ReactionRepository;
import com.craftsmanship.utils.CommonUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ReactionService {

    ReactionRepository reactionRepository;
    UserService userService;
    CommentService commentService;

    public ReactionService(ReactionRepository reactionRepository, UserService userService, CommentService commentService) {
        this.reactionRepository = reactionRepository;
        this.userService = userService;
        this.commentService = commentService;
    }

    public List<Users> getUsersReactingOnComment(String commentId, ReactionType type) {
        try {
            List<Reactions> likes = reactionRepository.findAllByReactionIdCommentIdAndType(commentId, type);
            List<String> userIds = likes.stream().map(like -> like.getReactionId().getUserId()).toList();
            return userService.gerUsers(userIds);
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to fetch likes/dislikes on comment with id: " + commentId);
        }
    }

    public void addReaction(ReactionRequest reactionRequest) {
        Thread t1=new Thread(()->commentService.increaseReactionCount(reactionRequest));
        Thread t2 = new Thread(saveReactionRunnable(reactionRequest));
        t1.start();
        try {
            t2.start();
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to add reaction on comment: " + reactionRequest);
        }
    }

    public void editReaction(ReactionRequest reactionRequest) {
        Thread t1=new Thread(()->setReactionCountOnAddingReaction(reactionRequest));
        Thread t2 = new Thread(saveReactionRunnable(reactionRequest));
        t1.start();
        try {
            t2.start();
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to add reaction on comment: " + reactionRequest);
        }

    }

    private Runnable saveReactionRunnable(ReactionRequest reactionRequest) {
        return () ->{
            Reactions reaction = CommonUtils.copyObjectProperties(reactionRequest, Reactions.class);
            if(reaction!=null) {
                reactionRepository.save(reaction);
            }
        };
    }

    public void deleteReaction(ReactionId reactionId) {
        try {
            Optional<Reactions> reactionsOptional = reactionRepository.findById(reactionId);
            if(reactionsOptional.isPresent()) {
                commentService.decreaseReactionCount(reactionsOptional.get());
                //here if modify count operation fails either we can stop deleting record from reaction table and throw
                // exception. Or we can continue and later a cron job can correct the count.
                // I am going with second approach as that is more user-friendly and showing incorrect count for a while
                // is not a big concern
                reactionRepository.deleteById(reactionId);
            }
        } catch (Exception exception) {
            throw new InternalServerErrorException("Failed to delete reaction: " + reactionId);
        }
    }

    private void setReactionCountOnAddingReaction(ReactionRequest reaction) {
        Optional<Reactions> reactionsOptional = reactionRepository.findById(reaction.getReactionId());
        if(reactionsOptional.isPresent()) {
            Reactions oldReaction = reactionsOptional.get();
            // if request for same reaction is dispatched again by same user on same comment then ignore it
            if(oldReaction.getType()!=reaction.getType()) {
                if(oldReaction.getType()==ReactionType.LIKE) {
                    //decrease like count and increase dislike count by 1
                    commentService.modifyReactionCount(reaction.getReactionId().getCommentId(), -1, 1);
                } else {
                    //increase like count and decrease dislike count by 1
                    commentService.modifyReactionCount(reaction.getReactionId().getCommentId(), 1, -1);
                }
            }
        } else {
            //increase reaction count
            commentService.increaseReactionCount(reaction);
        }
    }
}
