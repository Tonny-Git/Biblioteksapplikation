package com.company;

import java.io.Serializable;

public class Book implements Serializable {
    private String title;
    private String author;
    private String description;
    private boolean isAvailable = true;

    public Book(String title, String author, String description) {
        this.title = title;
        this.author = author;
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return  "" + title +
                "\n" + author +
                "\n" + description +
                "\n" + (isAvailable ? "This book can be rented" : "This book is already rented");
    }
}
