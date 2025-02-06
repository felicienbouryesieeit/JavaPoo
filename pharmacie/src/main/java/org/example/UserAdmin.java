package org.example;

public class UserAdmin extends User{


    public UserAdmin(String username, String password, StockUser stockuser) {
        super(username, password, stockuser);
    }

    @Override
    public void setUserType() {
        super.setUserType();
        usertype="Admin";
    }

    public void addUserAdmin(String username, String password,int usertype2) {
        stockuser.addUser(username, password,usertype2);
    }

    public void removeUserAdmin(int Index) {
        stockuser.removeUser(Index);
    }
}
