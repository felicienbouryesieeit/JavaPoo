package org.example;

public class UserPharmacist extends User {


    public UserPharmacist(String username, String password, StockUser stockuser) {
        super(username, password, stockuser);
    }

    @Override
    public void setUserType() {
        super.setUserType();
        usertype="Pharmacist";
        setUsertypeid(1);
    }

    @Override
    public void SaveFile() {

    }

    @Override
    public void LoadFile() {

    }
}
