package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.RestaurantEntity;

import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

public interface IRestaurantRepository extends JpaRepository<RestaurantEntity,Long> {
    Optional<List<RestaurantEntity>> findByUserId(Long userId);
    Optional<List<RestaurantEntity>> findByName(String user);
    Boolean existsByIdAndUserId(Long id, Long userId);
//    @Query("select e.name, e.url from RestaurantEntity e" )





}
