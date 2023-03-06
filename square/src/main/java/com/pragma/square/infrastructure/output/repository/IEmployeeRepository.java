package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface IEmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    Optional<EmployeeEntity>findByEmployeeId(Long employeeId);

}
