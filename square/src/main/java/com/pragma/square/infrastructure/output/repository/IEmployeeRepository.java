package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
}
