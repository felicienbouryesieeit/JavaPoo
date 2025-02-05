package org.example;

import java.util.ArrayList;

public class Order {
    ArrayList<Product> products;
    protected String ordertype;
    public Order() {

    }

    public String getOrdertype() {
        return ordertype;
    }
    public void setOrdertype(String ordertype) {
        this.ordertype = ordertype;
    }
    public ArrayList<Product> getProducts() {
        return products;
    }
    public void setProducts(ArrayList<Product> products) {
        this.products = products;
    }

    public void addProduct(String name, int quantity, Double price, String categoryName) {
        Product product = new Product(name, quantity, price, categoryName);
        this.products.add(product);
    }

    public void validateOrder(StockProduct listproducts ) {

        ArrayList<Product> allproducts =listproducts.getInventory();
        for(Product product : allproducts) {
            int index=0;
            for (Product product1 : products) {
                if (product.getName().equals(product1.getName())) {
                    listproducts.removeProduct(index);
                }
                index+=1;
            }
        }

    }
}
