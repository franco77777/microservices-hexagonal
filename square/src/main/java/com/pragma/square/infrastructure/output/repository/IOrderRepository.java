package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.OrderEntity;
import jakarta.persistence.criteria.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    Boolean existsByIdClient(Long idClient);
   Optional<Page<OrderEntity>>findAllByIdRestaurant_IdAndStatus(Long idRestaurant, String status, PageRequest pageable);


    Optional<OrderEntity> findByIdClient(Long userId);
}
