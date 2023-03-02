package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.CategoryEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ICategoryRepository extends JpaRepository<CategoryEntity,Long> {
   Optional<CategoryEntity> findByCategoryName(String name);


}
