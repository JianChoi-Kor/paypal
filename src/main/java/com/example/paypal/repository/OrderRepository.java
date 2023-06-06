package com.example.paypal.repository;

import com.example.paypal.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {

    Order findByPaypalOrderId(String paypalOrderId);
}