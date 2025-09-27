package com.jayant.Social_Media_Backend.requests;

import lombok.Data;

@Data
public class CreateCommentRequest {

    private Long id;
    private Long postId;
    private Long userId;
    private String text;
}

