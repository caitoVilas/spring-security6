package com.caito.security01.api;

import com.caito.security01.api.models.requests.AuthRequest;
import com.caito.security01.api.models.responses.AuthResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@Slf4j
@RequiredArgsConstructor
public class AuthController {

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest request){
        log.info("<<<< endpoint autenticacion >>>>");
        return null;
    }
}
