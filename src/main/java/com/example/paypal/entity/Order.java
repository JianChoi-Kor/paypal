package com.example.paypal.entity;

import lombok.Data;

import javax.persistence.*;

@Entity
@Table(name = "orders")
@Data
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "paypal_order_id")
    private String paypalOrderId;

    @Column(name = "paypal_order_status")
    private String paypalOrderStatus;
}