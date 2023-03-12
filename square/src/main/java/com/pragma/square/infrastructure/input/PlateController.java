package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IPlateHandler;
import com.pragma.square.application.request.PlateRequestDto;
import com.pragma.square.application.request.PlateUpdateRequestDto;
import com.pragma.square.application.response.PlateResponseDto;
import com.pragma.square.application.utils.PagesDto;
import com.pragma.square.infrastructure.output.entity.PlateEntity;
import com.pragma.square.infrastructure.output.repository.ICategoryRepository;
import com.pragma.square.infrastructure.output.repository.IPlateRepository;
import com.pragma.square.infrastructure.output.repository.IRestaurantRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/square/plate")
@RequiredArgsConstructor

public class PlateController {
    private final IPlateRepository plateRepository;
    private final IRestaurantRepository restaurantRepository;
    private final IPlateHandler plateHandler;
    private  final ICategoryRepository categoryRepository;


    @GetMapping()
    public List<PlateEntity> getAll (){
        List<PlateEntity> result = plateRepository.findAll();
        return result;
    }
    @GetMapping("/pagination/{categoryId}/{restaurantId}")
    public ResponseEntity<PagesDto<Page<PlateResponseDto>>> platesPage(@PathVariable("categoryId") Long categoryId, @PathVariable("restaurantId") Long restaurantId, @RequestParam int page, @RequestParam int size, @RequestParam String sort, @RequestParam String property) {
        Page<PlateResponseDto> response = plateHandler.getPlateResponseDtoByPage(categoryId,restaurantId,page,size,property,sort);
        return ResponseEntity.ok(new PagesDto<>(response.getSize(), response));
    }


    @PreAuthorize("hasRole('OWNER')")
    @PostMapping("/create/{categoryId}/{restaurantId}")
    public ResponseEntity<PlateResponseDto> create(@Valid @RequestBody  PlateRequestDto plate, @PathVariable("restaurantId") Long idRestaurant,@PathVariable("categoryId") Long categoryId){
        PlateResponseDto response = plateHandler.savePlate(plate,idRestaurant,categoryId);
        return  ResponseEntity.ok(response);
    }

    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/update/{id}")
    public ResponseEntity<PlateResponseDto> update(@Valid @RequestBody PlateUpdateRequestDto update, @PathVariable("id") Long plateId){
        PlateResponseDto response = plateHandler.updatePlate(update,plateId);
        return  ResponseEntity.ok(response);
    }
    @PreAuthorize("hasRole('OWNER')")
    @PutMapping("/deactivate/{id}")
    public ResponseEntity<PlateResponseDto> deactivatePlate(@PathVariable("id") Long plateId){
        PlateResponseDto response = plateHandler.deactivatePlate(plateId);
        return ResponseEntity.ok(response);
    }

}
