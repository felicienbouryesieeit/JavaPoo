package org.example;

public class UserClient extends User {


    public UserClient(String username, String password, StockUser stockuser) {
        super(username, password, stockuser);
    }

    @Override
    public void setUserType() {
        super.setUserType();
        usertype="Client";
    }
}
