package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.OrderEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IOrderRepository extends JpaRepository<OrderEntity,Long> {
    Boolean existsByIdClient(Long idClient);
}
