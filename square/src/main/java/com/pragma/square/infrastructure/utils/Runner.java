package com.pragma.square.infrastructure.utils;

import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final IRestaurantRepository restaurantRepository;

private final IPlateRepository plateRepository;
private final ICategoryRepository categoryRepository;



    @Override
    public void run(String... args) throws Exception {
        var restaurant = RestaurantEntity.builder()
                .nit("12343")
                .address("12343")
                .telephone("12343")
                .url("12343")
                .name("12343")
                .userId(1L)
                .build();
   RestaurantEntity result = restaurantRepository.save(restaurant);

        var category = CategoryEntity.builder()
                .categoryName("frying")
                .description("bad")
                .build();
        CategoryEntity categoryResult = categoryRepository.save(category);
        var category2 = CategoryEntity.builder()
                .categoryName("vegetables")
                .description("good")
                .build();
        CategoryEntity categoryResult2 = categoryRepository.save(category2);

        var plate = PlateEntity.builder()
                .name("12343")
                .price("12343")
                .description("12343")
                .imageUrl("12343")
                .idCategory(categoryResult)
                .active(true)
                .idRestaurant(result)
                .build();
PlateEntity response = plateRepository.save(plate);

        result.setPlateList(Set.of(response));
        RestaurantEntity result1 = restaurantRepository.save(result);
        System.out.println("soy plato");
        System.out.println(result1.toString());
    }}
