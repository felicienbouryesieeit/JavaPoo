package org.example;

import java.util.ArrayList;

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
        }