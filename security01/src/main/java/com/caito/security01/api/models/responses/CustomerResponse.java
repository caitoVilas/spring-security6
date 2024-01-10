package com.caito.security01.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerResponse implements Serializable {
    private Long id;
    private String username;
    private String name;
    private String role;
    private String jwt;
}
