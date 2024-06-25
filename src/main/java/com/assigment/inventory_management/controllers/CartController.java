package com.assigment.inventory_management.controllers;

import com.assigment.inventory_management.models.CartItem;
import com.assigment.inventory_management.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@CrossOrigin
public class CartController {
    @Autowired
    private CartService cartService;

    @PostMapping("/add")
    public ResponseEntity<List<CartItem>> addItemToCart(@RequestParam String email, @RequestParam Long itemId,
                                              @RequestParam int quantity) {
        cartService.updateItemQty(email, itemId, quantity, "add");
        List<CartItem> cartItems = cartService.getCartItems(email);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/remove")
    public ResponseEntity<List<CartItem>> removeItemFromCart(@RequestParam String email, @RequestParam Long itemId,
                                                        @RequestParam int quantity) {
        cartService.updateItemQty(email, itemId, quantity, "remove");
        List<CartItem> cartItems = cartService.getCartItems(email);
        return ResponseEntity.ok(cartItems);
    }

    @GetMapping("/{email}")
    public ResponseEntity<List<CartItem>> getCartItems(@PathVariable String email) {
        List<CartItem> cartItems = cartService.getCartItems(email);
        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/clear")
    public ResponseEntity<Void> clearCart(@RequestParam String email) {
        cartService.clearCart(email);
        return ResponseEntity.ok().build();
    }
}
