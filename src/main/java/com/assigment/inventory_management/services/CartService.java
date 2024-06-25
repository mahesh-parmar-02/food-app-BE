package com.assigment.inventory_management.services;

import com.assigment.inventory_management.models.CartItem;
import com.assigment.inventory_management.models.CartItemId;
import com.assigment.inventory_management.repository.CartItemRepository;
import com.assigment.inventory_management.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CartService {
    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private ProductRepository productRepository;

    public void updateItemQty(String email, Long itemId, int quantity, String operation) {
        CartItemId cartItemId = new CartItemId();
        cartItemId.setEmail(email);
        cartItemId.setItemId(itemId);
        CartItem cartItem = cartItemRepository.findById(cartItemId).orElse(new CartItem());
        cartItem.setId(cartItemId);
        if (operation == "add"){
            cartItem.setQuantity(cartItem.getQuantity() + quantity);
        }else {
            cartItem.setQuantity(cartItem.getQuantity() - quantity);
        }
        cartItem.setTotal(productRepository.findById(itemId).get().getPrice() * cartItem.getQuantity());

        cartItemRepository.save(cartItem);
    }

    public List<CartItem> getCartItems(String email) {
        return cartItemRepository.findAll().stream()
                                 .filter(cartItem -> cartItem.getId().getEmail().equals(email))
                                 .collect(Collectors.toList());
    }

    public void clearCart(String email) {
        List<CartItem> cartItems = getCartItems(email);
        for (CartItem cartItem : cartItems) {
            cartItemRepository.delete(cartItem);
        }
    }
}

