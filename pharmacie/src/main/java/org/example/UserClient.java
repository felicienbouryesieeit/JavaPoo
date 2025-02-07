package org.example;

public class UserClient extends User {


    public UserClient(String username, String password, StockUser stockuser) {
        super(username, password, stockuser);
    }
    //définit le type d'utilisateur
    @Override
    public void setUserType() {
        super.setUserType();
        usertype="Client";
        setUsertypeid(0);
    }

    @Override
    public void SaveFile() {

    }

    @Override
    public void LoadFile() {

    }
}
