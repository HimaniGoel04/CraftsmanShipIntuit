package com.craftsmanship.entities;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Sharded;

import java.time.LocalDateTime;

@Data
@Document
@Sharded(shardKey = {"commentId"})
public class Comments {

    @Id
    private String commentId;
    @Indexed
    private String commentOnId;
    private String commentText;
    private String commentBy;
    private Integer likesCount =0;
    private Integer dislikesCount=0;
    @CreatedDate
    private LocalDateTime createdAt;
    @LastModifiedDate
    private LocalDateTime updatedAt;

}
