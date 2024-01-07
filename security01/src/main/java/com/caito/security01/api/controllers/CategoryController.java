package com.caito.security01.api.controllers;

import com.caito.security01.api.models.requests.CategoryRequest;
import com.caito.security01.api.models.responses.CategoryResponse;
import com.caito.security01.infrastructure.services.contracts.CategoryService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/categories")
@Slf4j
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping
    public ResponseEntity<Page<CategoryResponse>> getAll(Pageable pageable){
        log.info("<<<< endpoint buscar todas las categorias >>>>");
        Page<CategoryResponse> responses = categoryService.getAll(pageable);
        if (responses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponse> getById(@PathVariable Long id){
        log.info("<<<< endpoint buscar categoria por id >>>>");
        return ResponseEntity.ok(categoryService.getById(id));
    }

    @PostMapping
    public ResponseEntity<CategoryResponse> create(@RequestBody CategoryRequest request){
        log.info("<<<< endpont crear categoria >>>>");
        return ResponseEntity.ok(categoryService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponse> update(@PathVariable Long id,
                                                   @RequestBody CategoryRequest request){
        log.info("<<<< endpont actualizar categoria >>>>");
        return ResponseEntity.ok(categoryService.update(id, request));
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<?> disble(@PathVariable Long id){
        log.info("<<<< endpoint desabilitar categoria >>>>");
        return ResponseEntity.ok().build();
    }
}
