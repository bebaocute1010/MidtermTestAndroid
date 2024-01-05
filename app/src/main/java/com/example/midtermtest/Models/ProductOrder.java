package com.example.midtermtest.Models;

public class ProductOrder {
    public Product product;
    public int quantity;

    public ProductOrder(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }
}
