package com.example.jevin.styleomega.Model;

/**
 * Created by Jevin on 20-Jul-17.
 */

public class User {

    private String nic;
    private String name;
    private String password;
    private String email;

    public User(String nic, String name, String password, String email) {
        this.nic = nic;
        this.name = name;
        this.password = password;
        this.email = email;
    }

    public User(){

    }

    public String getEmail() {  return email;   }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNic() {
        return nic;
    }

    public void setNic(String nic) {
        this.nic = nic;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
