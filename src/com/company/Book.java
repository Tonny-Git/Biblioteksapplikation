package com.company;

public class Book {
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

    @Override
    public String toString() {
        return  "" + title +
                " by " + author +
                "\n" + description +
                "\n" + (isAvailable ? "This book can be rented": "This book is already rented");
    }
}
