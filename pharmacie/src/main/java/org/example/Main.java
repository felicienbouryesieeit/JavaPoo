package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        StockProduct products = new StockProduct();
        products.addProduct("Doliprane 500mg",3,9.5,"Médicament");
        products.showProducts();
        products.productSearch("Doliprane 500mg");
        products.removeProduct(1);
        products.addProductRequest();
        products.showProducts();







    }
}