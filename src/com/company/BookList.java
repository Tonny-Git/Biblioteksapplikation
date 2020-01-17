package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class BookList {
    private ArrayList<Book> books = new ArrayList<>();
    private Scanner scanner = new Scanner(System.in);

    public void seeAllBooks() {
        for (int i = 0; i < books.size(); i++) {
            System.out.printf("%d. %s", i+1, books.get(i).getTitle());
        }
        seeBookAttributes();
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

    private void seeBookAttributes() {
        System.out.println("Select a book to see description/option or press 0 to exit");
        int bookChoice = userIntSelection();
        if (bookChoice == 0) {
            return;
        }
        try {
            System.out.println(books.get(bookChoice-1));
        } catch (Exception e) {
            System.out.println("This book dosen't exits");
        }
    }

    // Ej klar än
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
}
