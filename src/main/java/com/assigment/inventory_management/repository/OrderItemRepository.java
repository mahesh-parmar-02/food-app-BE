package com.assigment.inventory_management.repository;

import com.assigment.inventory_management.models.OrderItem;
import com.assigment.inventory_management.models.OrderItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderItemRepository extends JpaRepository<OrderItem, OrderItemId> {
}
