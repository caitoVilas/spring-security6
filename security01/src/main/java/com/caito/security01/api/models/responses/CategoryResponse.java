package com.caito.security01.api.models.responses;

import com.caito.security01.utils.enums.CategoryStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CategoryResponse implements Serializable {
    private Long id;
    private String name;
    private CategoryStatus status;
}
