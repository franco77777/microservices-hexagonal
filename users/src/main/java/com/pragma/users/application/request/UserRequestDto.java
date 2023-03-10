package com.pragma.users.application.request;




import com.pragma.users.application.validators.email.IEmailExists;
import com.pragma.users.application.validators.mobile.IMobileValidator;
import com.pragma.users.infrastructure.output.utils.AuthorityName;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Getter
@Setter
public class UserRequestDto {
@NotBlank(message = "name is required")
@NotNull(message = "name is required")
@NotEmpty(message = "name is required")
    private String firstname;
    @NotBlank(message = "surname is required")
    @NotNull(message = "surname is required")
    @NotEmpty(message = "surname is required")
    private String lastname;
    @NotBlank(message = "mobile is required")
    @NotNull(message = "cell phone is required")
    @NotEmpty(message = "cell phone is required")
    @Length(max = 14, message = "mobile should not have more than 14 digits")
    @IMobileValidator(message = "incorrect cell phone characters")
    private String mobile;

    @NotBlank(message = "password is required")
    @NotNull(message = "password is required")
    @NotEmpty(message = "password is required")
    private String password;

    @NotBlank(message = "email is required")
    @NotNull(message = "email is required")
    @NotEmpty(message = "email is required")
    @Email(message = "Email is not valid", regexp = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])")
    @IEmailExists(message = "Email already exists")
    private String email;
    private AuthorityName roles;
    @NotNull(message = "dni is required")
    @NotBlank(message = "dni is required")
    @NotEmpty(message = "dni is required")
    @Pattern(regexp="^[0-9]{4,13}$", message = "incorrect dni characters")
    private String dni;

}

