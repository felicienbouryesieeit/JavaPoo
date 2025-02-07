package org.example;

import java.io.IOException;
import java.util.ArrayList;

public class Product implements Serializable {
    private String name;
    private int quantity;
    private double price;
    private static int idCounter = 1;
    private int index;
    private Category category;
    private StockProduct stockProduct;
    //private Save save;

    public Product(String name, int quantity, double price, String categoryName) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
        this.index = idCounter++;
        this.category = new Category(categoryName);
        
        /*
        */

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


    @Override
    public void SaveFile() {

    }

    @Override
    public void LoadFile() {

    }


/*
    public void SaveFile2() throws IOException {
        ArrayList<String> arglist = new ArrayList<>();

        arglist.add(String.valueOf(this.name));
        arglist.add(String.valueOf(this.quantity));
        arglist.add(String.valueOf(this.price));
        arglist.add(String.valueOf(this.index));
        arglist.add(String.valueOf(this.category));

        Save save = new Save();
        System.out.println("livraison");
        save.writesavefile("dodo");

        //save.customwritesave(0,arglist);
    }*/
}
