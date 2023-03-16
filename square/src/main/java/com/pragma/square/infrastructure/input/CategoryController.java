package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.ICategoryHandler;
import com.pragma.square.application.request.CategoryRequestDto;
import com.pragma.square.application.response.CategoryResponseDto;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/square/category")
@RequiredArgsConstructor
public class CategoryController {
    private final ICategoryHandler categoryHandler;
    @PostMapping
    public ResponseEntity<CategoryResponseDto> create (@RequestBody @Valid CategoryRequestDto categoryRequestDto) {
      CategoryResponseDto responseDto = categoryHandler.createCategory(categoryRequestDto);
      return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
}
