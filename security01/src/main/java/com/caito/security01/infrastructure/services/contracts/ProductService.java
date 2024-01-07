package com.caito.security01.infrastructure.services.contracts;

import com.caito.security01.api.models.requests.ProductRequest;
import com.caito.security01.api.models.responses.ProductResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface ProductService {
    Page<ProductResponse> getAll(Pageable pageable);
    ProductResponse getById (Long id);
    ProductResponse create(ProductRequest request);
    ProductResponse update(Long id, ProductRequest request);
    void disble(Long id);
}
