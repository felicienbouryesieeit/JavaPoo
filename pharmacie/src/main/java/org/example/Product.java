package org.example;

public class Product {
    private String name;
    private int quantity;
    private double price;
    private static int idCounter = 1;
    private int index;
    private Category category;
    private StockProduct stockProduct;

    public Product(String name, int quantity, double price, String categoryName) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.index = idCounter++;
        this.category = new Category(categoryName);
    }
    public String getName() {
        return name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public int getIndex() {
        return index;
    }




}
