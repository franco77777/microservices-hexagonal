package com.pragma.square.infrastructure.configuration;

import com.pragma.square.domain.api.IRestaurantServicePort;
import com.pragma.square.domain.spi.IRestaurantPersistencePort;
import com.pragma.square.domain.usecase.RestaurantUseCase;
import com.pragma.square.infrastructure.output.adapter.RestaurantJpaAdapter;
import com.pragma.square.infrastructure.output.mapper.IRestaurantEntityMapper;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class BeanConfiguration {
    private final IRestaurantRepository restaurantRepository;
    private final IRestaurantEntityMapper restaurantEntityMapper;


    @Bean
    public IRestaurantPersistencePort RestaurantPersistencePort(){
        return new RestaurantJpaAdapter(restaurantRepository,restaurantEntityMapper);
    }
    @Bean
    public IRestaurantServicePort RestaurantServicePort(){
        return new RestaurantUseCase(RestaurantPersistencePort());
    }
}
