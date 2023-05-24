package com.gr1.spring.payload.response;

import lombok.*;

@Getter
@Setter
@Data
@NoArgsConstructor
@AllArgsConstructor
public class TokenRefreshResponse {
    private String accessToken;
    private String refreshToken;
    private String tokenType = "Bearer";

    public TokenRefreshResponse(String token, String requestRefreshToken) {
        this.accessToken=token;
        this.refreshToken=requestRefreshToken;
    }
}
