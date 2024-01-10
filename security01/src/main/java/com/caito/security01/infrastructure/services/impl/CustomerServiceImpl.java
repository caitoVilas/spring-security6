package com.caito.security01.infrastructure.services.impl;

import com.caito.security01.api.exceptions.customs.InvalidPasswordException;
import com.caito.security01.api.models.requests.CustomerRequest;
import com.caito.security01.api.models.responses.CustomerResponse;
import com.caito.security01.domain.entites.AppUser;
import com.caito.security01.domain.repositories.UserRepository;
import com.caito.security01.infrastructure.services.contracts.CustomerService;
import com.caito.security01.utils.enums.Role;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {
    private UserRepository userRepository;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;
    @Override
    public CustomerResponse register(CustomerRequest request) {
        this.validatePassword(request);
        var user = AppUser.builder()
                .name(request.getName())
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.ROLE_CUSTOMER)
                .build();
        var newUser = userRepository.save(user);
        var response = CustomerResponse.builder()
                .id(newUser.getId())
                .name(newUser.getName())
                .username(newUser.getUsername())
                .role(newUser.getRole().name())
                .build();
        String jwt = jwtService.generateToken(user, generateExtrClaims(newUser));
        response.setJwt(jwt);
        return response;
    }

    private void validatePassword(CustomerRequest request){
        if (!StringUtils.hasText(request.getPassword()) ||
                !StringUtils.hasText(request.getRepeatPassword())){
            throw new InvalidPasswordException("password empty");
        }

        if (!request.getPassword().equals(request.getRepeatPassword())){
            throw new InvalidPasswordException("password don't match");
        }
    }

    private Map<String, Object> generateExtrClaims(AppUser user){
        Map<String, Object> extra = new HashMap<>();
        extra.put("name", user.getName());
        extra.put("roles", user.getRole().name());
        extra.put("authorities", user.getAuthorities());
        return extra;
    }
}
