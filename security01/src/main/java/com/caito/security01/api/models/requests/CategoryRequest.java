package com.caito.security01.api.models.requests;

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
public class CategoryRequest implements Serializable {
    private String name;
    private CategoryStatus status;
}
