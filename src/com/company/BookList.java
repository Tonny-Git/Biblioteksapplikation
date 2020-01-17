package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BookList {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void seeAllBooks(boolean isAdmin) {
        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%d. %s%n", i+1, books.get(i).getTitle());
        }
        seeBookAttributes(isAdmin);
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

    private void seeBookAttributes(boolean isAdmin) {
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
            bookOptions(isAdmin, bookChoice);
            return;
        }
    }

    private void bookOptions(boolean isAdmin, int bookChoice) {
        boolean loop = true;
        while (loop) {
            System.out.println("What do you want to do?");
            if (isAdmin) {
                System.out.println("[1] Remove the book");
            } else  {

            }
            System.out.println("[0] Return to menu");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    if (isAdmin) {
                        removeOldBook(bookChoice);
                        loop = false;
                    } else {
                        System.out.println("Sorry wrong input!");
                    }
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
        Book book = books.remove(bookChoice-1);
        System.out.println("You removed " + book + " from the list.");
    }
}
