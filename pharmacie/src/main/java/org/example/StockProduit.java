package org.example;

import java.util.ArrayList;

class StockProduit implements Stockable {
    private ArrayList<Product> inventory = new ArrayList<>();

    @Override
    public void addProduct(String name, int quantity, Double price, String categoryName) {
        if ((price > 0) && (quantity>0)) {
            if ((name != null && !name.isEmpty()) && (categoryName != null && !categoryName.isEmpty())) {
                Product product = new Product(name,quantity,price,categoryName);
                inventory.add(product);
            }
        }




        System.out.println("Produit ajouté avec succès !");
    }

    public void showProducts() {
        if (inventory.isEmpty()) {
            System.out.println("L'inventaire est vide.");
        } else {
            System.out.println("Produits disponibles :");
            for (Product p : inventory) {
                System.out.println(p);
            }
        }
    }


}