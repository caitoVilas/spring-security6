package com.caito.security01.api.models.requests;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CustomerRequest implements Serializable {
    private String username;
    private String name;
    private String password;
    private String repeatPassword;
}
