package org.example;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

class StockProduct implements Stockable {
    private ArrayList<Product> inventory = new ArrayList<>();

    @Override
    public void addProduct(String name, int quantity, Double price, String categoryName) {
        if ((price > 0) && (quantity > 0)) {
            if ((name != null && !name.isEmpty()) && (categoryName != null && !categoryName.isEmpty())) {
                Product product = new Product(name, quantity, price, categoryName);
                inventory.add(product);
                System.out.println("Produit ajouté avec succès !");
            } else {
                System.out.println("Problème pendant l'ajout du produit.");
            }
        }
    }

    public void addProductRequest() {
        Scanner scanner = new Scanner(System.in);

        String name;
        int quantity;
        double price;
        String categoryName;

        while (true) {
            System.out.println("Quel est le nom du produit que vous souhaitez ajouter ?");
            name = scanner.nextLine().trim();
            if (!name.isEmpty()) break;
            System.out.println("Nom invalide. Veuillez réessayer.");
        }

        while (true) {
            System.out.println("Quelle est la quantité du produit ?");
            try {
                quantity = Integer.parseInt(scanner.nextLine().trim());
                if (quantity > 0) break;
                System.out.println("La quantité doit être un nombre positif.");
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre entier.");
            }
        }

        while (true) {
            System.out.println("Quel est le prix du produit ?");
            try {
                price = Double.parseDouble(scanner.nextLine().trim());
                if (price > 0) break;
                System.out.println("Le prix doit être un nombre positif.");
            } catch (NumberFormatException e) {
                System.out.println("Entrée invalide. Veuillez entrer un nombre valide.");
            }
        }

        while (true) {
            System.out.println("Quelle est la catégorie du produit ?");
            categoryName = scanner.nextLine().trim();
            if (!categoryName.isEmpty()) break;
            System.out.println("La catégorie ne peut pas être vide.");
        }

        addProduct(name, quantity, price, categoryName);
        System.out.println("Produit ajouté avec succès !");
    }


    @Override
    public void removeProduct(int id) {
        Product product = null;
        for (Product p : inventory) {
            if (p.getIndex() == id) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Êtes-vous sûr de vouloir supprimer le produit " + p.getName() + " à l'index " + p.getIndex() + "? (oui pour supprimer)");
                String answer = scanner.nextLine().trim().toLowerCase();
                if (answer.equals("oui")) {
                    product = p;
                } else {
                    System.out.println("Suppression annulée.");
                }
                break;
            }
        }
        if (product!=null) {
            System.out.println("Le produit " + product.getName() + " à été supprimé avec succès!");
            inventory.remove(product);
        }
    }

    public void showProducts() {
        if (inventory.isEmpty()) {
            System.out.println("L'inventaire est vide.");
        } else {
            System.out.println("Produits disponibles :");
            for (Product p : inventory) {
                System.out.println(p.getIndex() + " - " + p.getName() + " - " + p.getPrice() + " - " + p.getCategory().getName() + " - " + p.getQuantity() );
            }
        }
    }

    @Override
    public Product productSearch(String nom) {
        if (inventory.isEmpty()) {
            System.out.println("L'inventaire est vide.");
            return null;
        }

        inventory.sort(Comparator.comparing(Product::getName));

        int gauche = 0, droite = inventory.size() - 1;
        nom = nom.toLowerCase();

        while (gauche <= droite) {
            int milieu = gauche + (droite - gauche) / 2;
            String nomMilieu = inventory.get(milieu).getName().toLowerCase();

            int cmp = 0;
            int minLength = Math.min(nomMilieu.length(), nom.length());
            for (int i = 0; i < minLength; i++) {
                int diff = nomMilieu.charAt(i) - nom.charAt(i);
                if (diff != 0) {
                    cmp = diff;
                    break;
                }
            }            if (cmp == 0) {
                Product foundProduct = inventory.get(milieu);
                System.out.println("Produit trouvé : "
                        + foundProduct.getName()
                        + " - Quantité : " + foundProduct.getQuantity());
                return foundProduct;
            } else if (cmp < 0) {
                gauche = milieu + 1;
            } else {
                droite = milieu - 1;
            }
        }
        System.out.println("Le produit '" + nom + "' n'a pas été trouvé.");
        return null;
    }



public ArrayList<Product> getInventory() {
        return inventory;
}
}
