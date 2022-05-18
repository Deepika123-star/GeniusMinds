package com.genius.minds.HelperClass;

public class User {
    private int id;
    private String username;
    private String email;
    private String mobile;

    public User(int id, String username, String email, String mobile) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.mobile = mobile;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getEmail() {
        return email;
    }

    public String getMobile() {
        return mobile;
    }





}
