package com.hydrolink.api.auth.model.dto.request;

public record UserRequest(
        String fullName,
        String username,
        String password
) {
}
