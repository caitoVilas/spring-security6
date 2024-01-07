package com.caito.security01.infrastructure.services.impl;

import com.caito.security01.api.exceptions.customs.NotFoundException;
import com.caito.security01.api.models.requests.ProductRequest;
import com.caito.security01.api.models.responses.ProductResponse;
import com.caito.security01.domain.entites.Product;
import com.caito.security01.domain.repositories.CategoryRepository;
import com.caito.security01.domain.repositories.ProductRepository;
import com.caito.security01.infrastructure.services.contracts.ProductService;
import com.caito.security01.utils.cons.CategoryConst;
import com.caito.security01.utils.cons.ProductConst;
import com.caito.security01.utils.enums.ProductStatus;
import com.caito.security01.utils.mappers.ProductMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @Override
    public Page<ProductResponse> getAll(Pageable pageable) {
        log.info("---> inicio servicio buscar productos paginado");
        return productRepository.findAll(pageable).map(ProductMapper::mapToDto);
    }

    @Override
    public ProductResponse getById(Long id) {
        log.info("---> inicio servicio buscar producto por id");
        var product = productRepository.findById(id)
                .orElseThrow(()->{
                    log.error("ERROR: ".concat(ProductConst.P_ID_NOT_FOUND));
                    return new NotFoundException(ProductConst.P_ID_NOT_FOUND);
                });
        return ProductMapper.mapToDto(product);
    }

    @Override
    public ProductResponse create(ProductRequest request) {
        log.info("---> inicio servicio crear producto");
        var categoty = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> {
                    log.error("ERROR: ".concat(CategoryConst.C_ID_NOT_FOUND));
                    return new NotFoundException(CategoryConst.C_ID_NOT_FOUND);
                });
        var product = Product.builder()
                .name(request.getName())
                .price(request.getPrice())
                .status(ProductStatus.ENABLED)
                .category(categoty)
                .build();
        var productNew = productRepository.save(product);
        return ProductMapper.mapToDto(productNew);
    }

    @Override
    public ProductResponse update(Long id, ProductRequest request) {
        log.info("---> inicio servicio actualizar producto");
        var product = productRepository.findById(id)
                .orElseThrow(()->{
                    log.error("ERROR: ".concat(ProductConst.P_ID_NOT_FOUND));
                    return new NotFoundException(ProductConst.P_ID_NOT_FOUND);
                });
        var category = categoryRepository.findById(request.getCategory())
                .orElseThrow(() -> {
                    log.error("ERROR: ".concat(CategoryConst.C_ID_NOT_FOUND));
                    return new NotFoundException(CategoryConst.C_ID_NOT_FOUND);
                });
        product.setName(request.getName());
        product.setPrice(request.getPrice());
        product.setCategory(category);
        return ProductMapper.mapToDto(product);
    }

    @Override
    public void disble(Long id) {
        log.info("---> inicio servicio desabilitar producto");
        var producto = productRepository.findById(id)
                .orElseThrow(()->{
                    log.error("ERROR: ".concat(ProductConst.P_ID_NOT_FOUND));
                    return new NotFoundException(ProductConst.P_ID_NOT_FOUND);
                });
        producto.setStatus(ProductStatus.DISABLED);
        productRepository.save(producto);
    }
}
