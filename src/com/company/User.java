package com.company;

import java.util.ArrayList;

public class User extends Account {
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(String username, String password) {
        super(username, password);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }
}
