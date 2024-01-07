package com.caito.security01.utils.mappers;

import com.caito.security01.api.models.responses.CategoryResponse;
import com.caito.security01.domain.entites.Category;

public class CategoryMapper {

    public static CategoryResponse mapToDto(Category entity){
        return CategoryResponse.builder()
                .id(entity.getId())
                .name(entity.getName())
                .status(entity.getStatus())
                .build();
    }
}
