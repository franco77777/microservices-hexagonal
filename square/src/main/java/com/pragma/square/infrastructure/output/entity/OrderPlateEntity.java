//package com.pragma.square.infrastructure.output.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//
//@Entity
//@Table(name="orders_plates")
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor
//@Builder
//public class OrderPlateEntity {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    private Long id;
//    private Integer quantity;
//    @ManyToOne(fetch = FetchType.EAGER,optional = false)
//    @JoinColumn(name="plate_id")
//    private PlateEntity idPlate;
//    @ManyToOne(fetch = FetchType.EAGER,optional = false)
//    @JoinColumn(name="order_id")
//    private OrderEntity idOrder;
//}
