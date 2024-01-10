package com.caito.security01.infrastructure.services.impl;

import com.caito.security01.api.models.requests.AuthRequest;
import com.caito.security01.api.models.responses.AuthResponse;
import com.caito.security01.infrastructure.services.contracts.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final AuthenticationManager authenticationManager;
    @Override
    public AuthResponse login(AuthRequest request) {
        Authentication auth = new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getUsername());
        authenticationManager.authenticate(auth);
        SecurityContextHolder.getContext();
        return null;
    }
}
