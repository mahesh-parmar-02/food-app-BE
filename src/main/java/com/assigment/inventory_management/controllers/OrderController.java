package com.assigment.inventory_management.controllers;

import com.assigment.inventory_management.models.Order;
import com.assigment.inventory_management.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@CrossOrigin
public class OrderController {
    @Autowired
    private OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity<Order> placeOrder(@RequestParam String email, @RequestParam double totalAmount) {
        Order order = orderService.placeOrder(email, totalAmount);
        return ResponseEntity.ok(order);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<Order>> getOrderHistory(@PathVariable String email) {
        List<Order> orders = orderService.getOrderHistory(email);
        return ResponseEntity.ok(orders);
    }
}

