package com.jayant.Social_Media_Backend.requests;

import lombok.Data;

@Data
public class CreateLikeRequest {

    private Long id;
    private Long userId;
    private Long postId;

}
