package com.pragma.square.infrastructure.configuration;

import com.pragma.square.domain.api.IPlateServicePort;
import com.pragma.square.domain.spi.IPlatePersistencePort;
import com.pragma.square.domain.usecase.PlateUseCase;
import com.pragma.square.infrastructure.output.adapter.PlateJpaAdapter;
import com.pragma.square.infrastructure.output.mapper.IPlateEntityMapper;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class PlateBean {
    private final IPlateRepository plateRepository;
    private final IPlateEntityMapper plateEntityMapper;
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;
    private final ICategoryRepository categoryRepository;
    @Bean
    public IPlatePersistencePort platePersistencePort() {
        return new PlateJpaAdapter(plateRepository, plateEntityMapper,restaurantRepository,restaurantEntityMapper,categoryRepository);
    }
    @Bean
    public IPlateServicePort plateServicePort() {
        return new PlateUseCase(platePersistencePort());
    }
}
