package com.jayant.Social_Media_Backend.Dto;

import lombok.Data;

@Data
public class AuthenticationResponse {
    String message;
    Long userId;
    String refreshToken;
    String accessToken;
}
