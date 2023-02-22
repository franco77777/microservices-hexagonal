package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IObjectHandler;
import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user/register")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ObjectResController {
    private final IObjectHandler objectHandler;

    @PostMapping("/client")
    public ResponseEntity<UserResponseDto> saveClient(@RequestBody @Valid UserRequestDto userRequestDto){
        UserResponseDto responseDto =objectHandler.saveObject(userRequestDto, AuthorityName.ROLE_CLIENT);
        System.out.println("sout autoritye");
        System.out.println(AuthorityName.ROLE_CLIENT);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PreAuthorize("hasRole('CLIENT')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        return ResponseEntity.ok(objectHandler.getAllObjects());
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<UserResponseDto> saveAdmin(@RequestBody @Valid UserRequestDto userRequestDto){
        UserResponseDto responseDto = objectHandler.saveObject(userRequestDto, AuthorityName.ROLE_ADMIN);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/owner")
    public ResponseEntity<UserResponseDto> saveOwner(@RequestBody @Valid UserRequestDto userRequestDto){
        UserResponseDto responseDto = objectHandler.saveObject(userRequestDto, AuthorityName.ROLE_OWNER);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

   // @PreAuthorize("hasRole('EMPLOYEE')")
    @PostMapping("/employee")
    public ResponseEntity<UserResponseDto> saveEmployer(@RequestBody @Valid UserRequestDto userRequestDto){
        UserResponseDto responseDto = objectHandler.saveObject(userRequestDto, AuthorityName.ROLE_EMPLOYEE);
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

}
