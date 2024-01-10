package com.caito.security01.infrastructure.services.contracts;

import com.caito.security01.api.models.requests.CustomerRequest;
import com.caito.security01.api.models.responses.CustomerResponse;

public interface CustomerService {
    CustomerResponse register(CustomerRequest request);
}
