package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BookList {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void seeAllBooks(Account loggedInPerson) {
        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%d. %s%n", i+1, books.get(i).getTitle());
        }
        seeBookAttributes(loggedInPerson);
    }

    private int userIntSelection() {
        int answer;
        while (true) {
            try {
                answer = Integer.parseInt(scanner.nextLine());
                break;
            } catch (Exception e) {
                System.out.println("Wrong input. Please try again!");
            }
        }
        return answer;
    }

    private void seeBookAttributes(Account loggedInPerson) {
        while (true) {
            System.out.println("Select a book to see description/option or press 0 to exit");
            int bookChoice = userIntSelection();
            if (bookChoice == 0) {
                return;
            }
            try {
                System.out.println(books.get(bookChoice-1));
            } catch (Exception e) {
                System.out.println("This book dosen't exits");
                continue;
            }
            bookOptions(loggedInPerson, bookChoice);
            return;
        }
    }

    private void bookOptions(Account loggedInPerson, int bookChoice) {
        boolean loop = true;
        while (loop) {
            System.out.println("What do you want to do?");
            if (loggedInPerson instanceof Admin) {
                System.out.println("[1] Remove the book");
            } else  {
                System.out.println("[1] Rent the book");
            }
            System.out.println("[0] Return to menu");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    if (loggedInPerson instanceof Admin) {
                        removeOldBook(bookChoice-1);
                    } else if (loggedInPerson instanceof User){
                        User person = (User)loggedInPerson;
                        rentBook(person, bookChoice);
                    }
                    loop = false;
                    break;
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
        if (books.get(bookChoice-1).isAvailable()) {
            Book book = books.remove(bookChoice-1);
            System.out.println("You removed " + book + " from the list.");
        } else {
            System.out.println("You can't remove a borrowed book!");
        }
    }

    public void rentBook(User loggedInPerson, int bookChoice) {
        loggedInPerson.getBorrowedBooks().add(books.get(bookChoice-1));
        books.get(bookChoice-1).setAvailable(false);
        System.out.println("You rented the book: " + books.get(bookChoice-1));
    }
}
