package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.domain.models.PlateQuantityModel;
import com.pragma.square.infrastructure.output.entity.PlateQuantityEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPlateQuantityRepository extends JpaRepository<PlateQuantityEntity, Long> {

}
