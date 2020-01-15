package com.company;

public abstract class Account {
    private String username;
    private String password;

    public Account(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean isPasswordCorrect(String inputPassword) {

        if (password.equals(inputPassword)) {
            return true;
        }

        return false;
    }

    public String getUsername() {
        return username;
    }
}
