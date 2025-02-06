package org.example;

public abstract class User implements UserType {
    protected String username;
    protected String password;
    protected String usertype;
    protected StockUser stockuser;
    @Override
    public void setUserType() {

    }

    public User(String username,String password,StockUser stockuser) {
        this.username = username;
        this.password = password;
        this.stockuser = stockuser;
    }

    public String getUsername() {
        return username;
    }
    public void setUsername(String username) {
        this.username = username;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    public String getUsertype() {
        return usertype;
    }

}
