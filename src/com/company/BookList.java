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
        if (books.get(bookChoice-1).isAvailable()) {
            loggedInPerson.getBorrowedBooks().add(books.get(bookChoice-1));
            books.get(bookChoice-1).setAvailable(false);
            System.out.println("You rented the book: " + books.get(bookChoice-1).getTitle());
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
            System.out.println("Select a book to retun or press 0 to go back");

            try {
                int answer = Integer.parseInt(scanner.nextLine());
                if (answer == 0)
                    return;
                loggedInPerson.getBorrowedBooks().get(answer-1).setAvailable(true);
                loggedInPerson.getBorrowedBooks().remove(answer-1);
            } catch (Exception e) {
                System.out.println("This is wrong input!");
            }
        }

    }

    public void searchAfterBook(Account loggedInPerson) {
        System.out.println("Enter title or author of an book");
        String answer = scanner.nextLine().toLowerCase();
        ArrayList<Book> booksFound = new ArrayList<>();
        ArrayList<Integer> posistion = new ArrayList<>();

        for (int i = 0; i < books.size(); i++) {
            if (answer.equals(books.get(i).getTitle().toLowerCase()) || answer.equals(books.get(i).getAuthor().toLowerCase())) {
                booksFound.add(books.get(i));
                posistion.add(i);
            }
        }

        if (booksFound.size() ==  0)
            System.out.println("No Books found");
        else if (booksFound.size() == 1)
            bookOptions(loggedInPerson, posistion.get(0));
        else {
            System.out.println("Select a book or press 0 to exit");
            for (int i = 0; i < booksFound.size(); i++) {
                booksFound.get(i).getTitle();
                System.out.println(i+1 + " " + booksFound.get(i).getTitle());
            }

            int numberAnswer;
            try {
                numberAnswer = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Not a valid input!");
                return;
            }

            if (numberAnswer-1 >= 0 && numberAnswer < posistion.size()) {
                bookOptions(loggedInPerson, posistion.get(numberAnswer));
            } else if (numberAnswer == 0) {
                return;
            } else {
                System.out.println("Not a valid input!");
            }
        }
    }
}
