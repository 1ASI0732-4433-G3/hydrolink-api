package com.hydrolink.api.auth.facade;

import com.hydrolink.api.auth.model.entities.UserEntity;

public interface AuthenticationFacade {

    UserEntity getCurrentUser();
}
