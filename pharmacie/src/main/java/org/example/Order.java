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

    public void validateOrder(ArrayList<Product> orderproducts,StockProduct listproducts ) {

        ArrayList<Product> allproducts =listproducts.getInventory();
        setProducts(orderproducts);
        for(Product product : allproducts) {
            int index=0;
            for (Product product1 : orderproducts) {
                if (product.getName().equals(product1.getName())) {
                    listproducts.removeProduct(index);
                }
                index+=1;
            }
        }

    }
}
