package com.pragma.users.infrastructure.output.entity;

import com.pragma.users.infrastructure.output.utils.AuthorityName;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="users")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    private String username;
    //@Column(columnDefinition = "TEXT")

    private String surname;

    private String mobile;

    private String password;

    @Column(unique = true)
    private String email;
    private String dni;

@Enumerated(EnumType.STRING)
    private AuthorityName roles;


    @Override
    public String toString() {
        return "SecurityUser{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", dni='" + dni + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", surname='" + surname + '\'' +
                ", roles='" + roles + '\'' +
                '}';
    }

}