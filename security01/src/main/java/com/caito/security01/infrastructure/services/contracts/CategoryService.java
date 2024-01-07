package com.caito.security01.infrastructure.services.contracts;

import com.caito.security01.api.models.requests.CategoryRequest;
import com.caito.security01.api.models.responses.CategoryResponse;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface CategoryService {
    Page<CategoryResponse> getAll(Pageable pageable);
    CategoryResponse getById(Long id);
    CategoryResponse create(CategoryRequest request);
    CategoryResponse update(Long id, CategoryRequest request);
    void disable(Long id);
}
