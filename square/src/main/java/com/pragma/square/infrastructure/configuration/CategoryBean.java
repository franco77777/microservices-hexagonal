package com.pragma.square.infrastructure.configuration;

import com.pragma.square.domain.api.ICategoryServicePort;
import com.pragma.square.domain.spi.ICategoryPersistencePort;
import com.pragma.square.domain.usecase.CategoryUseCase;
import com.pragma.square.infrastructure.output.adapter.CategoryAdapter;
import com.pragma.square.infrastructure.output.mapper.ICategoryEntityMapper;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class CategoryBean {

    private final ICategoryRepository categoryRepository;
    private final ICategoryEntityMapper categoryEntityMapper;
    @Bean
    public ICategoryPersistencePort categoryPersistencePort1() {
        return new CategoryAdapter(categoryRepository,categoryEntityMapper);}

        @Bean ICategoryServicePort categoryServicePor2t() {
            return new CategoryUseCase(categoryPersistencePort1());
        }


}
