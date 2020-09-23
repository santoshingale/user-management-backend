package com.bridgelabz.usermanagement.dto;

import com.bridgelabz.usermanagement.model.UserData;

public class TokenResponseDTO {
    public String token;
    public UserData userData;

    public TokenResponseDTO(String token) {
        this.token = token;
    }
}
