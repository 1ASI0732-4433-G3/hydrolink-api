package com.hydrolink.api.auth.model.dto.response;

public record AuthResponse(
        String username,
        String message,
        Boolean status,
        String jwt) {
}