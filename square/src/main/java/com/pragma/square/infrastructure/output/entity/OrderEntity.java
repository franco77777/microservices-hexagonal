package com.pragma.square.infrastructure.output.entity;
import com.fasterxml.jackson.annotation.JsonIncludeProperties;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;
import java.util.List;

@Entity
@Table(name="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long idClient;
    private Date orderDate;
    private String status;
    private Long idChef;
    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "id_restaurant")
    private RestaurantEntity idRestaurant;
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "order_plates",
            joinColumns = @JoinColumn(name = "order_id"),
            inverseJoinColumns = @JoinColumn(name = "plate_id")
    )
    private List<PlateEntity> plates;

    @OneToMany(fetch = FetchType.EAGER,cascade = CascadeType.ALL)
    @JoinColumn(name="quantity_id")
    private List<PlateQuantityEntity> quantity;


}
