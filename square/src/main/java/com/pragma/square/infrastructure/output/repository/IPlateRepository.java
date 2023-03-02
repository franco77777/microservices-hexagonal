package com.pragma.square.infrastructure.output.repository;

import com.pragma.square.infrastructure.output.entity.PlateEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;

public interface IPlateRepository extends JpaRepository<PlateEntity,Long> {


    Optional<Page<PlateEntity>> findAllByIdCategory_IdAndIdRestaurant_Id(Long id, Long userId, PageRequest pageable);



}
