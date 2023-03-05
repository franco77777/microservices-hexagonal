package com.pragma.square.application.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;


@Data
public class CategoryRequestDto {
    @NotBlank(message = "Category name is required")
    @NotNull(message = "Category name is required")
    private String categoryName;
    @NotBlank(message = "Category description is required")
    @NotNull(message = "Category description is required")
    private String description;
}
