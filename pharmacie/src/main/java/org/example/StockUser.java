package org.example;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class StockUser {
        private ArrayList<User> inventory= new ArrayList<>();
        private Save save = new Save();

        public StockUser(Save save) {
                this.save = save;
        }

        //permet de créer un nouver utilisateur et de l'ajouter à la liste d'utilisateurs
        public void addUser(String username, String password,int usertype) {

                User user = new UserClient(username, password,this);
                //Le switch usertype définit quel type de client il faut créer
                switch (usertype) {
                        case 0:
                                user = new UserClient(username, password,this);
                                break;

                        case 1:
                                user = new UserPharmacist(username, password,this);
                                break;
                        case 2:
                                user = new UserAdmin(username, password,this);
                                break;
                }

                inventory.add(user);
        }
        //permet de retirer un utilisateur de la liste d'utilisateurs
        public void removeUser(int index) {
                inventory.remove(index);
        }
        public User getUser(int index) {
                return inventory.get(index);
        }

        public ArrayList<User> getInventory() {
                return inventory;
        }

        //permet d'afficher chaque utilisateur
        public void showinventory() {
                for (User user : inventory) {
                        user.setUserType();
                        System.out.println(
                                "username : "+user.getUsername()+
                                        " password : "+user.getPassword()+
                                        " type : "+user.getUsertype()
                        );

                }

        }



        //permet de se connecter en tant qu'utilisateur à l'aide de la CLI
        public void connection() {
                Scanner scanner = new Scanner(System.in);
                //permet d'écrire son identifiant dans la commande
                System.out.println("Entrez votre identifiant.");
                String answer = scanner.nextLine().trim().toLowerCase();
                boolean usernametrue = false;
                User connectinguser = null;
                //vérifie tout les noms de tout les utilisateurs pour voir si l'un correspond à celui qui a été écrit
                for (User user : inventory) {
                        System.out.println("nom : "+user.getUsername());
                        if (answer.equals(user.getUsername())) {
                                //usernametrue définit si l'un des utilisateurs correspond, et connectinguser renvoie l'utilisateur en question
                                usernametrue = true;
                                connectinguser = user;
                        }
                }
                if (usernametrue) {
                        System.out.println("Identifiant correct");
                        connectionpassword(connectinguser);
                } else {
                        System.out.println("Identifiant incorrect");
                }
        }











        //2ème étape de la connection, permet de rentrer son mot de passe
        public void connectionpassword(User connectinguser) {
                //permet d'écrire le mot de passe
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez votre mot de passe.");
                String answer = scanner.nextLine().trim().toLowerCase();

                //vérifie si le mot de passe de l'utilisateur choisi correspond à ce qui a été écrit
                if (answer.equals(connectinguser.getPassword())) {
                        connectinguser.setUserType();
                        System.out.println("Connection reussie. Bienvenue "+connectinguser.getUsertype()+" "+connectinguser.getUsername()+"!" );
                } else {
                        System.out.println("Mot de passe incorrect");
                }
        }














        public void addUsertosave(String username, String password,int usertype) throws IOException {
                addUser(username,password,usertype);
                refreshstocksave();
                //save.setInventory(inventory);
        }

        public void refreshstocksave() throws IOException {
                save.setInventoryindex(inventory);
                save.setsavetext();
        }





        }