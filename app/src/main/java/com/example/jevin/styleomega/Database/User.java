package com.example.jevin.styleomega.Database;

/**
 * Created by Jevin on 20-Jul-17.
 */

public class User {

    private int nic;
    private String name;
    private String password;

    public User(int nic, String name, String password) {
        this.nic = nic;
        this.name = name;
        this.password = password;
    }

    public User(){

    }

    public int getNic() {
        return nic;
    }

    public void setNic(int nic) {
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
