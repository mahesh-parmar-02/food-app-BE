package com.assigment.inventory_management.repository;

import com.assigment.inventory_management.models.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}

