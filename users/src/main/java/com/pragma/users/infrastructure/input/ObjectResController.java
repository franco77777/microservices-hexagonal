package com.pragma.users.infrastructure.input;

import com.pragma.users.application.handler.IObjectHandler;
import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.domain.model.AuthenticationModel;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.entity.UserEntity;
import com.pragma.users.infrastructure.output.services.UserService;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user/register")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class ObjectResController {
    private final UserService userService;
    private final IObjectHandler objectHandler;

//    @PostMapping("/client")
//    public ResponseEntity<UserResponseDto> saveClient(@RequestBody @Valid UserRequestDto userRequestDto){
//        UserResponseDto responseDto =objectHandler.saveObject(userRequestDto, AuthorityName.ROLE_CLIENT);
//        System.out.println("sout autoritye");
//        System.out.println(AuthorityName.ROLE_CLIENT);
//        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
//    }

    @PostMapping("/client")
    public ResponseEntity<UserResponseDto> saveClient(@RequestBody @Valid UserRequestDto userRequestDto){
        UserResponseDto responseDto =objectHandler.saveObject(userRequestDto, AuthorityName.ROLE_CLIENT);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }
    @PostMapping("/test")
    public ResponseEntity<TokenDto> saveClient(@RequestBody @Valid UserEntity userRequestDto){
        TokenDto responseDto = userService.registerToken(userRequestDto);

        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @PostMapping("/authenticate")
    public ResponseEntity<TokenDto> authenticate(
            @RequestBody AuthenticationModel request
    ) {
        return ResponseEntity.ok(userService.authenticate(request));}

    @PostMapping("/validate")
    public ResponseEntity<TokenDto> validateUser(@RequestParam String token){
        TokenDto tokenDto = userService.validate(token);
        if(tokenDto == null)
            return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(tokenDto);
    }
    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/all")
    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
        System.out.println("soy security");
        System.out.println(SecurityContextHolder.getContext());
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
