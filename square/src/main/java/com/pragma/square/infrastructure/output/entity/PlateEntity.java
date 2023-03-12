package com.pragma.square.infrastructure.output.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="plates")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlateEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;

    private String price;

    private String description;

    private String imageUrl;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_category")

    private CategoryEntity idCategory;
    private Boolean active;
    @ManyToMany(fetch = FetchType.EAGER,mappedBy = "plates")
    @JsonIgnore
    private List<OrderEntity> order;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_restaurant")
    @JsonIncludeProperties(value = {"id"})
    private RestaurantEntity idRestaurant;






}
