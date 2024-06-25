package com.assigment.inventory_management.services;

import com.assigment.inventory_management.models.*;
import com.assigment.inventory_management.repository.OrderItemRepository;
import com.assigment.inventory_management.repository.OrderRepository;
import com.assigment.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Autowired
    private CartService cartService;

    public Order placeOrder(String email, double totalAmount) {
        Order order = new Order();
        order.setEmail(email);
        order.setTotalAmount(totalAmount);
        order.setDelivered(false);
        order = orderRepository.save(order);

        List<CartItem> cartItems = cartService.getCartItems(email);
        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();
            OrderItemId orderItemId = new OrderItemId();
            orderItemId.setOrderId(order.getOrderId());
            orderItemId.setItemId(cartItem.getId().getItemId());
            orderItem.setId(orderItemId);
            orderItem.setQuantity(cartItem.getQuantity());
            orderItemRepository.save(orderItem);

            //reduce qty
            Product item = productRepository.findById(cartItem.getId().getItemId()).orElse(null);
            item.setQty(item.getQty() - cartItem.getQuantity());
            productRepository.save(item);
        }

        cartService.clearCart(email);
        return order;
    }

    public List<Order> getOrderHistory(String email) {
        return orderRepository.findAll().stream()
                              .filter(order -> order.getEmail().equals(email))
                              .collect(Collectors.toList());
    }
}
