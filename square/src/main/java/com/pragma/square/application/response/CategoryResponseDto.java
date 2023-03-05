package com.pragma.square.application.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor

@Data
public class CategoryResponseDto {
    private Long id;
    private String categoryName;
    private String description;
}
