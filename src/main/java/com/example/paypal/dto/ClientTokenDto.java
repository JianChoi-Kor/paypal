package com.example.paypal.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data
public class ClientTokenDto {
    @JsonProperty("client_token")
    private String clientToken;
    //AccessTokenResponseDto expiresIn -> int??
    @JsonProperty("expires_in")
    private Long expiresIn;
}
