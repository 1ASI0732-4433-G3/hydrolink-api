package com.hydrolink.api.auth.services;

import com.hydrolink.api.auth.model.dto.request.LoginRequest;
import com.hydrolink.api.auth.model.dto.request.SignupRequest;
import com.hydrolink.api.auth.model.dto.response.AuthResponse;
import com.hydrolink.api.auth.model.entities.UserEntity;

/**
 * Interface for authentication services, providing methods for user login and registration.
 */
public interface AuthService {
    /**
     * Logs in a user with the provided credentials.
     *
     * @param loginRequest The login request containing user credentials.
     * @return An {@link AuthResponse} object containing the authentication result.
     */
    AuthResponse login(LoginRequest loginRequest);

    /**
     * Registers a new user with the specified details and password.
     *
     * @param signupRequest The {@link SignupRequest} object containing user details.
     * @return An {@link AuthResponse} object containing the authentication result.
     */
    AuthResponse signup(SignupRequest signupRequest);

    void logout();

    UserEntity getAuthenticatedUser();
}