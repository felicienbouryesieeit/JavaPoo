package org.example;

public class UserAdmin extends User{


    public UserAdmin(String username, String password, StockUser stockuser) {
        super(username, password, stockuser);
    }

    @Override
    public void setUserType() {
        super.setUserType();
        usertype="Admin";
        setUsertypeid(2);
    }

    //ajouter un utilisateur en tant qu'admin
    public void addUserAdmin(String username, String password,int usertype2) {
        stockuser.addUser(username, password,usertype2);
    }
    //retirer un utilisateur en tant qu'admin
    public void removeUserAdmin(int Index) {
        stockuser.removeUser(Index);
    }

    @Override
    public void SaveFile() {

    }

    @Override
    public void LoadFile() {

    }
}
