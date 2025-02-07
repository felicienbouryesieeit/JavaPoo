package org.example;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;
import java.util.ArrayList;

class StockProduct implements Stockable {
    private ArrayList<Product> inventory = new ArrayList<>();
    private Save save = new Save();

    public StockProduct(Save save) {
        this.save = save;
        save.setStockProduct(this);
        if (this.inventory == null) {
            this.inventory = new ArrayList<>();
        }
    }



    @Override
    public void addProduct(String name, int quantity, Double price, String categoryName) {
        if ((price > 0) && (quantity > 0)) {
            if ((name != null && !name.isEmpty()) && (categoryName != null && !categoryName.isEmpty())) {
                Product product = new Product(name, quantity, price, categoryName);

                inventory.add(product);
                System.out.println("Produit ajouté et sauvegardé avec succès !");
            } else {
                System.out.println("Problème pendant l'ajout du produit.");
            }
        }
    }

    public Product productRequest() {
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

        return new Product(name, quantity, price, categoryName);
    }

    public void addProductRequest() {
        addProduct(this.productRequest().getName(),this.productRequest().getQuantity(),this.productRequest().getPrice(),this.productRequest().getCategory().getName());
    }

    //permet de retirer des produits après la validation d'une commande
    public void removeProductOrder(int id,int quantity) {
        //compare la quantité de produits dans l'inventaire par rapport à la quantité de produits commandés
        if (inventory.get(id).getQuantity()<quantity) {
            System.out.println("Pas assez de "+inventory.get(id).getName()+"!");
        } else {
            inventory.get(id).setQuantity(inventory.get(id).getQuantity()-quantity);
            if (inventory.get(id).getQuantity()==0) {
                System.out.println("Plus de "+inventory.get(id).getName()+"!");
                inventory.remove(id);
            } else {
                if (inventory.get(id).getQuantity()<5) {
                    System.out.println(inventory.get(id).getName()+" bientot en rupture de stock!");
                }
            }
        }
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
            System.out.println("Le produit " + product.getName() + " à été supprimé et sauvegardé avec succès!");
            inventory.remove(product);
        }
    }

    public void removeProductRequest() {
        this.showProducts();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Veuillez entrer l'ID du produit que vous souhaiter supprimer.");
        int answer = Integer.parseInt(scanner.nextLine().trim());
        removeProduct(answer);
    }

    public void showProducts() {
        if (inventory.isEmpty()) {
            System.out.println("L'inventaire est vide.");
        } else {
            System.out.println("Produits disponibles :");
            for (Product p : inventory) {
                System.out.println(p.getName() + " (id: " + p.getIndex() + ")" + " - " + p.getPrice() + "€ - Quantité: " + p.getQuantity() + " - " + p.getCategory().getName());
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

    public void productQuantityCheck() {
        ArrayList<Product> inventory2 = new ArrayList<>(inventory);
        int n = inventory2.size();

        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                // Comparaison des quantités des produits
                if (inventory2.get(j).getQuantity() > inventory2.get(j + 1).getQuantity()) {
                    // Échange des produits j et j+1
                    Product temp = inventory2.get(j);
                    inventory2.set(j, inventory2.get(j + 1));
                    inventory2.set(j + 1, temp);
                }
            }
        }

        if (inventory2.isEmpty()) {
            System.out.println("L'inventaire est vide.");
        } else {
            System.out.println("Produits disponibles :");
            for (Product p : inventory2) {
                if (p.getQuantity()>5) {
                    System.out.println(p.getQuantity() + " - "+ p.getName() + " (id: " + p.getIndex() + ")" + " - " + p.getPrice() + "€ - " + p.getCategory().getName());
                } else {
                    System.out.println("/!\\ " + p.getQuantity() + " - "+ p.getName() + " (id: " + p.getIndex() + ")" + " - " + p.getPrice() + "€ - " + p.getCategory().getName());
                }
            }
        }
    }

public ArrayList<Product> getInventory() {
        return inventory;
}


    public void addStocktosave(String name, int quantity, Double price, String categoryName) throws IOException {
        addProduct(name, quantity, price, categoryName);
        System.out.println("chamas");
        refreshstocksave();
        //save.setInventory(inventory);
    }

    public void refreshstocksave() throws IOException {
        save.setInventory(inventory);
        save.setsavetext();
    }

    public void removeProductOrderSave(int id,int quantity) throws IOException {
        removeProductOrder(id, quantity);
        refreshstocksave();
    }


}
