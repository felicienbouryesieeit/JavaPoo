package org.example;

public interface Stockable {

    void addProduct(String name, int quantity, Double price, String categoryName);

    void removeProduct(int id);

    Product productSearch (String nom);
}
