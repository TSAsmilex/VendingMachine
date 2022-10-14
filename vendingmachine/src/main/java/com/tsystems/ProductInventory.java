package com.tsystems;

public class ProductInventory {
    private Product product;
    private int stock;
    private int price;

    public ProductInventory(Product product, int stock, int price) {
        this.product = product;
        this.stock = stock;
        this.price = price;
    }


    public Product getProduct() {
        return product;
    }


    public int getStock() {
        return stock;
    }


    public int getPrice() {
        return price;
    }


    public void setStock(int quantity) {
        this.stock = quantity;
    }


    public void setPrice(int price) {
        this.price = price;
    }


    public void setProduct(Product product) {
        this.product = product;
    }


    public boolean isAvailable() {
        return stock > 0;
    }


    public boolean decreaseStock() {
        if (stock > 0) {
            stock--;
            return true;
        }
        return false;
    }
}
