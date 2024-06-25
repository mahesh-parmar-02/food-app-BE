package com.assigment.inventory_management.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class CartItemId implements Serializable {
    private String email;
    private Long itemId;

    // Getters and setters
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Override
    public boolean equals(Object o) {

        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        CartItemId that = (CartItemId) o;
        return email.equals(that.email) && itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {

        int result = email.hashCode();
        result = 31 * result + itemId.hashCode();
        return result;
    }
}

