package com.caito.security01.api.controllers;

import com.caito.security01.api.models.requests.ProductRequest;
import com.caito.security01.api.models.responses.ProductResponse;
import com.caito.security01.infrastructure.services.contracts.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/products")
@Slf4j
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;

    @GetMapping
    public ResponseEntity<Page<ProductResponse>> getAll(Pageable pageable){
        log.info("<<<< buscar todos los productos paginados >>>>");
        Page<ProductResponse> responses = productService.getAll(pageable);
        if (responses.isEmpty())
            return ResponseEntity.noContent().build();
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ProductResponse> getById(@PathVariable Long id){
        log.info("<<<< endpoint buscar producto por id >>>>");
        return ResponseEntity.ok(productService.getById(id));
    }

    @PostMapping
    public ResponseEntity<ProductResponse> create(@RequestBody ProductRequest request){
        log.info("<<<< endpont crear producto >>>>");
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(productService.create(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductResponse> update(@PathVariable Long id,
                                                  @RequestBody ProductRequest request){
        log.info("<<<< endpoint actualizar producto >>>>");
        return ResponseEntity.ok(productService.update(id, request));
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<?> disable(@PathVariable Long id){
        log.info("<<<< endpoint desabilitar producto >>>>");
        productService.disble(id);
        return ResponseEntity.ok().build();
    }
}
