package com.jayant.Social_Media_Backend.requests;

import lombok.Data;

@Data
public class RefreshTokenRequest {

    private Long userId;
    private String refreshToken;
}
