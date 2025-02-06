package org.example;

import java.util.ArrayList;
import java.util.Scanner;

public class StockUser {
        private ArrayList<User> inventory= new ArrayList<>();

        public StockUser() {}

        public void addUser(String username, String password,int usertype) {

                User user = new UserClient(username, password,this);
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
        public void removeUser(int index) {
                inventory.remove(index);
        }
        public User getUser(int index) {
                return inventory.get(index);
        }

        public ArrayList<User> getInventory() {
                return inventory;
        }

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




        public void connection() {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez votre identifiant.");
                String answer = scanner.nextLine().trim().toLowerCase();
                boolean usernametrue = false;
                User connectinguser = null;
                for (User user : inventory) {
                        System.out.println("nom : "+user.getUsername());
                        if (answer.equals(user.getUsername())) {
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












        public void connectionpassword(User connectinguser) {
                Scanner scanner = new Scanner(System.in);
                System.out.println("Entrez votre mot de passe.");
                String answer = scanner.nextLine().trim().toLowerCase();


                if (answer.equals(connectinguser.getPassword())) {
                        connectinguser.setUserType();
                        System.out.println("Connection reussie. Bienvenue "+connectinguser.getUsertype()+" "+connectinguser.getUsername()+"!" );
                } else {
                        System.out.println("Mot de passe incorrect");
                }
        }




        }