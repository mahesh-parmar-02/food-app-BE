package com.assigment.inventory_management.models;

import jakarta.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
public class OrderItemId implements Serializable {
    private Long orderId;
    private Long itemId;

    // Getters and setters
    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
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

        OrderItemId that = (OrderItemId) o;
        return orderId.equals(that.orderId) && itemId.equals(that.itemId);
    }

    @Override
    public int hashCode() {

        int result = orderId.hashCode();
        result = 31 * result + itemId.hashCode();
        return result;
    }
}

