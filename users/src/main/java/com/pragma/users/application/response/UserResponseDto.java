package com.pragma.users.application.response;

import com.pragma.users.infrastructure.output.utils.AuthorityName;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponseDto {
    private Long id;
    private String username;
    private String surname;

    private String mobile;

    private String password;

    private String email;

    private AuthorityName roles;
    private String dni;

}

