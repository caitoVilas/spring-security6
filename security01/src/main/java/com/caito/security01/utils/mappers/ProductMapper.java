package com.caito.security01.utils.mappers;

import com.caito.security01.api.models.responses.ProductResponse;
import com.caito.security01.domain.entites.Product;

public class ProductMapper {

    public static ProductResponse mapToDto(Product entity){
        return ProductResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .status(entity.getStatus().name())
                .category(CategoryMapper.mapToDto(entity.getCategory()))
                .build();
    }
}
