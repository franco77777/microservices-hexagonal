package com.pragma.square.infrastructure.output.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="plate_quantity")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class PlateQuantityEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long plateId;
    private Integer quantity;

    @ManyToOne()
    @JsonIgnore
    @JoinColumn(name="quantity_id", insertable=false, updatable=false)
    OrderEntity order;

}
