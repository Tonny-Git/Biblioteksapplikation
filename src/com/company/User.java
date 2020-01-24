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

    public void printNameOfBorrowedBooks() {
        for (int i = 0; i < borrowedBooks.size(); i++) {
            System.out.println(i+1 + " " + borrowedBooks.get(i).getTitle());
        }
    }
}
