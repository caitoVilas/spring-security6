package com.caito.security01.infrastructure.services.impl;

import io.jsonwebtoken.Header;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import io.jsonwebtoken.Jwts;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
@Slf4j
public class JwtService {
    private final String SECRET_KEY = "Hw9z1Yk8Nmq1IzlwcCg8j6yHzw6RKjzZUi9r7Ww555o0PP" ;

    public String generateToken(UserDetails user, Map<String, Object> extraClaims){
        Date issuedAt = new Date(System.currentTimeMillis());
        Date expiration = new Date((30 * 60 * 1000) + issuedAt.getTime());
        String jwt = Jwts.builder()
                .setClaims(extraClaims)
                .setSubject(user.getUsername())
                .setIssuedAt(issuedAt)
                .setExpiration(expiration)
                .setHeaderParam(Header.TYPE, Header.JWT_TYPE)
                .signWith(generateKey(), SignatureAlgorithm.HS256)
                .compact();
        return jwt;
    }

    private Key generateKey(){
        byte[] key = Decoders.BASE64.decode(SECRET_KEY);
        return Keys.hmacShaKeyFor(key);
    }
}
