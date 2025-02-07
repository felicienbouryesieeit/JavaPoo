package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.time.LocalDate;
import java.time.LocalTime;

public class Order implements Serializable {
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
    //Permet d'ajouter un produit à la liste de commandes
    public void addProduct(String name, int quantity, Double price, String categoryName) {
        Product product = new Product(name, quantity, price, categoryName);
        this.products.add(product);
    }
    //Permet de valider une commande, l'argument listproducts permet de récupérer l'inventaire ou effectuer la commande
    public void validateOrder(StockProduct listproducts ) throws IOException {
        String orderLogText = ("Commande du : " + LocalDate.now() + " à " + LocalTime.now().withNano(0) + " : ");

        ArrayList<Product> allproducts =listproducts.getInventory();
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
                orderLogText += ("\nProduit commandé : " + itemtoremovequantity + " " + allproducts.get(itemtoremove).getName() + " (id: " + allproducts.get(itemtoremove).getIndex() +
                        ") " + allproducts.get(itemtoremove).getPrice() + "€ - " + allproducts.get(itemtoremove).getCategory().getName());
                listproducts.removeProductOrderSave(itemtoremove,itemtoremovequantity);
            }
        }
        orderHistory.add(0,orderLogText);
    }

    public void displayOrderLog() {
        for (String orderHistory : orderHistory) {
            System.out.println(orderHistory);
        }
    }

    @Override
    public void SaveFile() {

    }

    @Override
    public void LoadFile() {

    }


}
