package com.caito.security01.api.controllers;

import com.caito.security01.api.models.requests.CustomerRequest;
import com.caito.security01.api.models.responses.CustomerResponse;
import com.caito.security01.infrastructure.services.contracts.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/customers")
@Slf4j
@RequiredArgsConstructor
public class CustomerController {
    private final CustomerService customerService;

    @PostMapping
    public ResponseEntity<CustomerResponse> register(@RequestBody CustomerRequest request){
        log.info("<<<< endpoint registrar usuario >>>>");
        return ResponseEntity.status(HttpStatus.CREATED).body(customerService.register(request));
    }
}
