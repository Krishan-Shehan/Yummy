package com.example.yummy.Model;

public class User {
    private String username;
    private String phone;
    private String password;

    public User() {
    }

    public User(String name, String phone, String password) {
        this.username = name;
        this.phone = phone;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
