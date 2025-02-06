package org.example;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.

        StockProduct products = new StockProduct();
        products.addProduct("Doliprane 500mg",6,9.5,"Médicament");
        products.addProduct("Advil 500mg",3,9.5,"Médicament");
        products.addProduct("Orange 500mg",1,9.5,"Médicament");
        products.addProduct("Tomate 500mg",1945,9.5,"Médicament");
        products.productQuantityCheck();
        products.showProducts();

        OrderStandard standard = new OrderStandard();
        standard.addProduct("Doliprane 500mg",2,9.5,"Médicament");
        standard.addProduct("Advil 500mg",3,9.5,"Médicament");

        standard.validateOrder(products);
        System.out.println("apres : ");
        products.showProducts();

        OrderStandard standard2 = new OrderStandard();
        standard2.addProduct("Orange 500mg",1,9.5,"Médicament");
        standard2.addProduct("Tomate 500mg",3,9.5,"Médicament");

        standard2.validateOrder(products);

        standard2.displayOrderLog();
        /*
        products.productSearch("Doliprane 500mg");
        products.removeProduct(1);
        products.addProductRequest();
        products.showProducts();
        */









    }
}