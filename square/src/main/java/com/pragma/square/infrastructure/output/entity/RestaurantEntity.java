package com.pragma.square.infrastructure.output.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
  @OneToMany(mappedBy = "idRestaurant", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JsonIgnore
   private Set<PlateEntity> plateList = new HashSet<>();
}
