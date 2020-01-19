package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class User extends Account {
    private ArrayList<Book> borrowedBooks = new ArrayList<>();

    public User(String username, String password) {
        super(username, password);
    }

    public ArrayList<Book> getBorrowedBooks() {
        return borrowedBooks;
    }

    public void setBorrowedBooks(ArrayList<Book> borrowedBooks) {
        this.borrowedBooks = borrowedBooks;
    }
}
