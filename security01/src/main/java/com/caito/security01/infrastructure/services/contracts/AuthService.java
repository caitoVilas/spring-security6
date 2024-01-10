package com.caito.security01.infrastructure.services.contracts;

import com.caito.security01.api.models.requests.AuthRequest;
import com.caito.security01.api.models.responses.AuthResponse;

public interface AuthService {
    AuthResponse login(AuthRequest request);
}
