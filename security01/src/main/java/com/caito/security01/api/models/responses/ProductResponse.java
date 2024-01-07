package com.caito.security01.api.models.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.math.BigDecimal;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class ProductResponse implements Serializable {
    private Long id;
    private String name;
    private BigDecimal price;
    private String status;
    private CategoryResponse category;
}
