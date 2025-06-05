package com.kelompok2.selfservicesapp.dto;

import java.util.List;

public class CheckoutRequest {

    private Integer customerId;

    private List<Item> items;

    public static class Item {
        private Long productId;
        private Integer quantity;

        // Getter & Setter
        public Long getProductId() {
            return productId;
        }

        public void setProductId(Long productId) {
            this.productId = productId;
        }

        public Integer getQuantity() {
            return quantity;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }
    }

    // Getter & Setter

    public Integer getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Integer customerId) {
        this.customerId = customerId;
    }

    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }
}
