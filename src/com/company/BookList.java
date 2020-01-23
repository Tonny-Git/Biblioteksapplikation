package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BookList {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    //Ta Bort senare
    public BookList() {
        books.add(new Book("Harry Potter", "Jk Rowling", "Harry and his friends"));
        books.add(new Book("Harry Potter 2", "Jk Rowling", "Harry and his friends"));
        books.add(new Book("Zelda", "Nintendo", "Link try to save Zelda"));
    }

    public void seeAllBooks(Account loggedInPerson) {
        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%d. %s%n", i+1, books.get(i).getTitle());
        }
        seeBookAttributes(loggedInPerson, books);
    }

    private void seeBookAttributes(Account loggedInPerson, ArrayList<Book> books) {
        if (loggedInPerson instanceof User) {
            System.out.println("Select a book to see description/option or press 0 to exit");
        } else {
            System.out.println("Select a book to remove it or press 0 to exit");
        }
        int bookChoice = MethodUtility.intSelectionArray(books.size());
        if (bookChoice != -1 && loggedInPerson instanceof User) {
            System.out.println(books.get(bookChoice));
            bookOptions(loggedInPerson, books.get(bookChoice));
        } else if (bookChoice != -1) {
            removeOldBook(bookChoice);
        }
    }

    private void bookOptions(Account loggedInPerson, Book book) {
        if (loggedInPerson instanceof Admin) {
            return;
        }
        while (true) {
            System.out.println("What do you want to do?");
            System.out.println("[1] Rent the book");
            System.out.println("[0] Return to menu");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    rentBook((User) loggedInPerson, book);
                    return;
                case "0":
                    return;
                default:
                    System.out.println("Sorry wrong input!");
                    break;
            }
        }
    }

    public void addNewBook() {
        System.out.println("Enter title");
        String title = scanner.nextLine();
        System.out.println("Enter author");
        String author = scanner.nextLine();
        System.out.println("Enter description");
        String description = scanner.nextLine();

        books.add(new Book(title, author, description));
        System.out.println(title + " got added to the app\n" + "Press enter to continue. . .");
        scanner.nextLine();
    }

    public void removeOldBook(int bookChoice) {
        if (books.get(bookChoice).isAvailable()) {
            Book book = books.remove(bookChoice);
            System.out.println("You removed " + book + " from the list.");
        } else {
            System.out.println("You can't remove a borrowed book!");
        }
    }

    public void rentBook(User loggedInPerson, Book book) {
        if (book.isAvailable()) {
            loggedInPerson.getBorrowedBooks().add(book);
            book.setAvailable(false);
            System.out.println("You rented the book: " + book.getTitle());
        } else {
            System.out.println("This book is already rented!");
        }
    }

    public void showAndReturnBorrowedBook(User loggedInPerson) {
        if (loggedInPerson.getBorrowedBooks().size() == 0) {
            System.out.println("You have not rented any books!");
        } else {
            for (int i = 0; i < loggedInPerson.getBorrowedBooks().size(); i++) {
                System.out.println(i+1 + " " +loggedInPerson.getBorrowedBooks().get(i).getTitle());
            }
            System.out.println("Select a book to return or press 0 to go back");
            int answer = MethodUtility.intSelectionArray(loggedInPerson.getBorrowedBooks().size());
            if (answer != -1) {
                loggedInPerson.getBorrowedBooks().get(answer).setAvailable(true);
                loggedInPerson.getBorrowedBooks().remove(answer);
            }
        }
    }

    public void searchAfterBook(Account loggedInPerson) {
        System.out.println("Enter title or author of an book");
        String answer = scanner.nextLine().toLowerCase();
        ArrayList<Book> booksFound = new ArrayList<>();

        int i = 1;
        for (Book book : books) {
            if (answer.equals(book.getTitle().toLowerCase()) || answer.equals(book.getAuthor().toLowerCase())) {
                booksFound.add(book);
                System.out.println(i+1 + " " + book.getTitle());
                i++;
            }
        }

        if (booksFound.size() ==  0)
            System.out.println("No Books found");
        else if (booksFound.size() == 1)
            bookOptions(loggedInPerson, booksFound.get(0));
        else {
            System.out.println("Select a book or press 0 to exit");
            int bookAnswer = MethodUtility.intSelectionArray(booksFound.size());
            if (bookAnswer != -1) {
                bookOptions(loggedInPerson, booksFound.get(bookAnswer));
            }
        }
    }

    public void searchThroughArray (Account loggedInPerson, String option) {
        ArrayList<Book> booksFound = new ArrayList<>();
        int j = 1;
        for (int i = 0; i < books.size(); i++) {
            if (option.equals("ShowAllAvailableBooks")) {
                if (books.get(i).isAvailable()) {
                    booksFound.add(books.get(i));
                    System.out.printf("%d. %s%n", j+1, books.get(i).getTitle());
                    j++;
                }

            } else if (option.equals("ShowAllBorrowedBooks")) {
                if (!books.get(i).isAvailable()) {
                    booksFound.add(books.get(i));
                    System.out.printf("%d. %s%n", j+1, books.get(i).getTitle());
                    j++;
                }
            }
        }
        seeBookAttributes(loggedInPerson, booksFound);
    }
}