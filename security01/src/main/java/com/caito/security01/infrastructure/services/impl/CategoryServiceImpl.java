package com.caito.security01.infrastructure.services.impl;

import com.caito.security01.api.exceptions.customs.NotFoundException;
import com.caito.security01.api.models.requests.CategoryRequest;
import com.caito.security01.api.models.responses.CategoryResponse;
import com.caito.security01.domain.entites.Category;
import com.caito.security01.domain.repositories.CategoryRepository;
import com.caito.security01.infrastructure.services.contracts.CategoryService;
import com.caito.security01.utils.cons.CategoryConst;
import com.caito.security01.utils.enums.CategoryStatus;
import com.caito.security01.utils.mappers.CategoryMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public Page<CategoryResponse> getAll(Pageable pageable) {
        log.info("---> inicio servicio buscar categorias paginadas");
        return categoryRepository.findAll(pageable).map(CategoryMapper::mapToDto);
    }

    @Override
    public CategoryResponse getById(Long id) {
        log.info("---> inicio servicio buscar categoria por id");
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ERROR: ".concat(CategoryConst.C_ID_NOT_FOUND));
                    return new NotFoundException(CategoryConst.C_ID_NOT_FOUND);
                });
        return CategoryMapper.mapToDto(category);
    }

    @Override
    public CategoryResponse create(CategoryRequest request) {
        log.info("---> inicio servicio crear categoria");
        var category = Category.builder()
                .name(request.getName())
                .status(CategoryStatus.ENABLED)
                .build();
        var categoryNew = categoryRepository.save(category);
        return CategoryMapper.mapToDto(categoryNew);
    }

    @Override
    public CategoryResponse update(Long id, CategoryRequest request) {
        log.info("---> inicio servicio actualizar categoria");
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ERROR: ".concat(CategoryConst.C_ID_NOT_FOUND));
                    return new NotFoundException(CategoryConst.C_ID_NOT_FOUND);
                });
        category.setName(request.getName());
        categoryRepository.save(category);
        return CategoryMapper.mapToDto(category);
    }

    @Override
    public void disable(Long id) {
        log.info("---> inicio servicio desabilitar categoria");
        var category = categoryRepository.findById(id)
                .orElseThrow(() -> {
                    log.error("ERROR: ".concat(CategoryConst.C_ID_NOT_FOUND));
                    return new NotFoundException(CategoryConst.C_ID_NOT_FOUND);
                });
        category.setStatus(CategoryStatus.DISABLED);
        categoryRepository.save(category);
    }
}
