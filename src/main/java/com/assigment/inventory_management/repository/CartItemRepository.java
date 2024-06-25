package com.assigment.inventory_management.repository;

import com.assigment.inventory_management.models.CartItem;
import com.assigment.inventory_management.models.CartItemId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, CartItemId> {
}
