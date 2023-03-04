package com.pragma.square.infrastructure.output.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Table(name="restaurant_employee")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeEntity {
    @Id
    @GeneratedValue
    private Long id;
    private Long restaurantId;
    private Long employeeId;

}
