package com.pragma.square.infrastructure.utils;

import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import com.pragma.square.infrastructure.output.entity.OrderEntity;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.entity.RestaurantEntity;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import com.pragma.square.infrastructure.output.repository.IOrderRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
@RequiredArgsConstructor
public class Runner implements CommandLineRunner {

    private final IRestaurantRepository restaurantRepository;

private final IPlateRepository plateRepository;
private final ICategoryRepository categoryRepository;
private final IOrderRepository orderRepository;
private final IPlateRepository orderPlateRepository;



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
RestaurantEntity restaurant1 = RestaurantEntity.builder()
        .id(result.getId())
        .build();

        var plate2 = PlateEntity.builder()
                .name("123435")
                .price("123435")
                .description("123435")
                .imageUrl("123435")
                .idCategory(categoryResult2)
                .active(true)
                .idRestaurant(restaurant1)
                .build();
        PlateEntity response2 = plateRepository.save(plate2);

        result.setPlateList(Set.of(response));
        RestaurantEntity result1 = restaurantRepository.save(result);
        System.out.println("soy plato");
        System.out.println(result1.toString());

        List<PlateEntity> plateList = new ArrayList<>();
        plateList.add(response);
        plateList.add(response2);


        OrderEntity order = OrderEntity.builder()
                .idClient(1L)
                .orderDate(new Date())
                .status("pending")
                .idChef(null)
                .idRestaurant(result)
                .plates(plateList)
                .build();
        orderRepository.save(order);

Set<Long> ids = new HashSet<>();
ids.add(1L);
ids.add(1L);
ids.add(2L);
        System.out.println("soy ids");
        System.out.println(ids);
        System.out.println(ids.size());

    }}
