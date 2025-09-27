package com.jayant.Social_Media_Backend.requests;

import lombok.Data;

@Data
public class CreatePostRequest {

    private Long id;
    private Long userId;
    private String text;
    private String title;
}
