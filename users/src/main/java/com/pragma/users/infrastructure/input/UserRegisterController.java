package com.pragma.users.infrastructure.input;
import com.pragma.users.application.handler.IUserHandler;
import com.pragma.users.application.mapper.IUserRequestMapper;
import com.pragma.users.application.request.UserRequestDto;
import com.pragma.users.application.response.TokenResponseDto;
import com.pragma.users.application.response.UserResponseDto;
import com.pragma.users.infrastructure.output.entity.TokenDto;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import com.pragma.users.infrastructure.output.utils.SquareService;
import com.pragma.users.infrastructure.security.JwtService;
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
public class UserRegisterController {

    private final IUserHandler objectHandler;
    private final IUserRequestMapper objectRequestMapper;
    private final SquareService squareService;





//    @GetMapping("/all")
//    public ResponseEntity<List<UserResponseDto>> getAllUsers(){
//
//        System.out.println("soy security");
//        System.out.println(SecurityContextHolder.getContext());
//        return ResponseEntity.ok(objectHandler.getAllUsers());
//    }

    @PostMapping("/client")
    public ResponseEntity<TokenResponseDto> saveClient(@RequestBody @Valid  UserRequestDto userRequestDto){
        TokenResponseDto TokenResponseDto =objectHandler.saveUser(userRequestDto, AuthorityName.ROLE_CLIENT);

        return new ResponseEntity<>(TokenResponseDto, HttpStatus.CREATED);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin")
    public ResponseEntity<TokenResponseDto> saveAdmin(@RequestBody @Valid UserRequestDto userRequestDto){
        TokenResponseDto Token =objectHandler.saveUser(userRequestDto, AuthorityName.ROLE_ADMIN);
        return new ResponseEntity<>(Token, HttpStatus.CREATED);
    }

   @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/owner")
    public ResponseEntity<UserResponseDto> saveOwner(@RequestBody @Valid UserRequestDto userRequestDto){
       UserResponseDto userResponseDto =objectHandler.saveOwner(userRequestDto, AuthorityName.ROLE_OWNER);
        return new ResponseEntity<>(userResponseDto, HttpStatus.CREATED);
    }

   @PreAuthorize("hasRole('OWNER')")
   @PostMapping("/employee/{restaurantId}")
    public ResponseEntity<String> saveEmployer(@RequestBody @Valid UserRequestDto userRequestDto, @PathVariable("restaurantId") Long restaurantId){
       UserResponseDto responseDto =objectHandler.saveOwner(userRequestDto, AuthorityName.ROLE_EMPLOYEE);
        Long userId = responseDto.getId();
       try {
           String OwnerId = SecurityContextHolder.getContext().getAuthentication().getCredentials().toString();
           squareService.createRestaurantEmployee(Long.parseLong(OwnerId),restaurantId,responseDto.getId());
           return new ResponseEntity<>("employee: " + responseDto.getEmail() + " created", HttpStatus.CREATED);
       } catch (Exception e) {
           objectHandler.deleteUser(userId);
           return new ResponseEntity<>("bad request: "+e.getMessage(), HttpStatus.BAD_REQUEST);
       }
    }


}
