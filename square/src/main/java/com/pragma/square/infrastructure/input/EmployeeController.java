package com.pragma.square.infrastructure.input;

import com.pragma.square.application.handler.IEmployeeHandler;
import com.pragma.square.infrastructure.output.entity.EmployeeEntity;
import com.pragma.square.infrastructure.output.repository.IEmployeeRepository;
import jakarta.ws.rs.Path;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/square/employee")
@RequiredArgsConstructor
public class EmployeeController {
    private final IEmployeeHandler employeeHandler;
    private final IEmployeeRepository employeeRepository;
    @PostMapping("/{OwnerId}/{restaurantId}/{employeeId}")
    public ResponseEntity<Boolean> createEmployee(@PathVariable("OwnerId") Long OwnerId, @PathVariable("restaurantId") Long restaurantId,@PathVariable("employeeId") Long employeeId) {
        employeeHandler.createEmployee(OwnerId,restaurantId,employeeId);
        return new ResponseEntity<>(true, HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<EmployeeEntity>> get (){
        return ResponseEntity.ok(employeeRepository.findAll());
    }
}
