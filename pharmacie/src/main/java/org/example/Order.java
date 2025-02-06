package org.example;

import java.util.ArrayList;
import java.time.LocalDate;

public class Order {
    ArrayList<Product> products=new ArrayList<>();
    private static ArrayList<String> orderHistory=new ArrayList<>();


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
        String orderLogText = ("Commande du : " + LocalDate.now() + " à " + );

        ArrayList<Product> allproducts =listproducts.getInventory();
        //System.out.println("pitre :"+allproducts.size()+"  "+productsName.size());
        for(Product product1 : products) {
            int itemtoremove=-1;
            int itemtoremovequantity=0;
            int index=0;
            for (Product product : allproducts) {
                if (product1.getName().equals(product.getName())) {
                    itemtoremove=index;
                    itemtoremovequantity=product1.getQuantity();

                }
                index+=1;
            }
            if (itemtoremove!=-1) {
            listproducts.removeProductOrder(itemtoremove,itemtoremovequantity);
            }
        }
        System.out.println(orderLogText);

    }
}
