package org.example;
import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws IOException {
        //TIP Press <shortcut actionId="ShowIntentionActions"/> with your caret at the highlighted text
        // to see how IntelliJ IDEA suggests fixing it.
        Save save = new Save();

        StockProduct products = new StockProduct(save);
        ArrayList<Order> orders = new ArrayList<>();


        //Création des produits dans le stock des produits
        products.addProduct("Pommade",6,3.3,"Médicament");
        products.addProduct("Doliprane 500mg",6,9.5,"Médicament");
        products.addProduct("Advil 500mg",3,10.25,"Médicament");
        products.addProduct("Ibuprophène 500mg",100,12.5,"Médicament");
        products.addProduct("Pansement mercurochrome",1945,3.2,"Bandage");

        /*
        products.productQuantityCheck();

        orders.add(new OrderStandard());
        orders.get(0).addProduct("Doliprane 500mg",2,9.5,"Médicament");
        orders.get(0).addProduct("Advil 500mg",3,9.5,"Médicament");
        orders.get(0).validateOrder(products);
        System.out.println("apres : ");
        products.showProducts();


         */
        StockUser stockUser = new StockUser(save);

        //Création des utilisateurs dans l'objet "StockUser"
        stockUser.addUser("michel","banane0",0);
        stockUser.addUser("louis","tomate0",1);
        stockUser.addUser("emilie","patate1",2);
        stockUser.showinventory();


        if (stockUser.getInventory().get(2) instanceof UserAdmin) {
            UserAdmin userAdmin = (UserAdmin) stockUser.getInventory().get(2) ;
            //userAdmin.removeUserAdmin(0);
           }
        System.out.println("apres : ");
        stockUser.showinventory();

        // CLI
        Scanner scanner = new Scanner(System.in);
        String commande;
        while (true) {
            System.out.print(">");
            commande = scanner.nextLine().trim();
            if (commande.equals("-help")) {
                System.out.println("Les commandes disponibles sont : \n" +
                        "-help : Affiche la liste des commandes.\n" +
                        "-quit : Permet de quitter la CLI.\n" +
                        "-ajouterproduit : Permet d'ajouter un produit.\n" +
                        "-retirerproduit : Permet de retirer un produit.\n" +
                        "-connexion : Permet de se connecter à un compte.\n" +
                        "-afficherstocks : Affiche les stocks de produits.\n" +
                        "-chercheproduit : Recherche un produit.\n" +
                        "-retirerutilisateur : Supprime un utilisateur par ID.\n" +
                        "-listeutilisateurs : Affiche la liste des utilisateurs.\n" +
                        "-nouvellecommande : Ajoute une nouvelle commande.\n" +
                        "-validercommande : Valide une commandes\n" +
                        "-affichercommandes : Affiche les commandes.");
            } else if (commande.equals("-quit")) {
                break;
            } else if (commande.equals("-ajouterproduit")) {
                products.addProductRequest();
            } else if (commande.equals("-retirerproduit")) {
                products.removeProductRequest();
            } else if (commande.equals("-connexion")) {
                stockUser.connection();
            } else if (commande.equals("-afficherstocks")) {
                products.productQuantityCheck();
            } else if (commande.equals("-chercheproduit")) {
                System.out.println("Quel produit?");
                String produit = scanner.nextLine().trim();
                products.productSearch(produit);
            } else if (commande.equals("-retirerutilisateur")) {
                System.out.println("Quel utilisateur? (Index)");

                ArrayList<User> userList = stockUser.getInventory(); // Récupère la liste des utilisateurs

                for (int i = 0; i < userList.size(); i++) {
                    User user = userList.get(i);
                    System.out.println("Utilisateur: " + user.getUsername() + " | ID: " + (i + 1));
                }

                int usertokill = Integer.parseInt(scanner.nextLine().trim());
                if (usertokill > 0 && usertokill <= userList.size()) {
                    stockUser.removeUser(usertokill - 1);
                    System.out.println("Utilisateur supprimé avec succès.");
                } else {
                    System.out.println("ID invalide. Veuillez entrer un nombre valide.");
                }
            } else if (commande.equals("-listeutilisateurs")) {
                ArrayList<User> userList = stockUser.getInventory(); // Récupère la liste des utilisateurs

                System.out.println("Utilisateurs:");
                for (int i = 0; i < userList.size(); i++) {
                    User user = userList.get(i);
                    System.out.println("Utilisateur: " + user.getUsername() + " | ID: " + (i + 1));
                }
            } else if (commande.equals("-nouvellecommande")) {
                Product product;
                orders.add(new Order());
                while (true) {
                    System.out.println("Ajouter un produit? oui pour ajouter.");
                    String answer = scanner.nextLine().trim();
                    if (answer.equals("oui")) {
                        product = products.productRequest();
                        orders.get(orders.size() - 1).addProduct(product.getName(), product.getQuantity(), product.getPrice(), product.getCategory().getName());
                    } else {
                        break;
                    }
                }
            } else if (commande.equals("-affichercommandes")) {
                if (orders.isEmpty()) {
                    System.out.println("Aucune commande en cours.");
                } else {
                    for (int i = 0; i < orders.size(); i++) {
                        System.out.println("Commande " + (i + 1) + " :");
                        for (Product product : orders.get(i).getProducts()) {
                            System.out.println("Produit: " + product.getName() + ", Quantité: " + product.getQuantity() + ", Prix: " + product.getPrice() + ", Catégorie: " + product.getCategory().getName());
                        }
                        System.out.println("----------------------");
                    }
                }
            } else if (commande.equals("-validercommande")) {
                if (orders.isEmpty()) {
                    System.out.println("Aucune commande en cours.");
                } else {
                    for (int i = 0; i < orders.size(); i++) {
                        System.out.println("Commande " + (i + 1) + " :");
                        for (Product product : orders.get(i).getProducts()) {
                            System.out.println("Produit: " + product.getName() + ", Quantité: " + product.getQuantity() + ", Prix: " + product.getPrice() + ", Catégorie: " + product.getCategory().getName());
                        }
                        System.out.println("----------------------");
                    }
                    System.out.println("Quelle commande à valider?");
                    int valider = Integer.parseInt(scanner.nextLine().trim());
                    orders.get(valider-1).validateOrder(products);
                    orders.remove(valider-1);
                }
            } else if (commande.equals("-creercompte")) {
                System.out.println("Quel pseudo?");
                String username = scanner.nextLine().trim();
                System.out.println("Quel mot de passe?");
                String password = scanner.nextLine().trim();
                System.out.println("Quel type d'utilisateur? 0:Client, 1:Pharmacien, 2:Admin");
                int usertype = Integer.parseInt(scanner.nextLine().trim());
                stockUser.addUser(username,password,1);
            }
        }

        System.out.println("apres apres : ");
        products.showProducts();
    }
}