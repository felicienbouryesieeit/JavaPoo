package org.example;

public abstract class User implements UserType, Serializable {
    private static final long serialVersionUID = 1L;
    protected String username;
    protected String password;
    protected String usertype;
    protected StockUser stockuser;
    protected Integer usertypeid;

    public Integer getUsertypeid() {
        return usertypeid;
    }
    public void setUsertypeid(Integer usertypeid) {
        this.usertypeid = usertypeid;
    }
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
