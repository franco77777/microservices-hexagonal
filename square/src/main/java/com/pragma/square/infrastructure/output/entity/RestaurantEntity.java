package com.pragma.square.infrastructure.output.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="restaurants")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RestaurantEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private String telephone;
    private String url;
    private Long userId;
    private String nit;
}
