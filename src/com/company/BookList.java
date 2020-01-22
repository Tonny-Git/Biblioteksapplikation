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
        seeBookAttributes(loggedInPerson);
    }

    //Ta Bort senare
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
        System.out.println("Select a book to see description/option or press 0 to exit");
        int bookChoice = MethodUtility.intSelectionArray(books.size());
        if (bookChoice != -1) {
            System.out.println(books.get(bookChoice));
            bookOptions(loggedInPerson, books.get(bookChoice));
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
        if (books.get(bookChoice-1).isAvailable()) {
            Book book = books.remove(bookChoice-1);
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

    //Forsätt härifrån
    // Working on fixing old methods
    public ArrayList<Book> searchThroughArray (String option) {
        ArrayList<Book> booksFound = new ArrayList<>();
        for (int i = 0; i < books.size(); i++) {
            if (option.equals("ShowAllAvailableBooks")) {
                if (books.get(i).isAvailable())
                    booksFound.add(books.get(i));
            } else if (option.equals("ShowAllBorrowedBooks")) {
                if (!books.get(i).isAvailable())
                    booksFound.add(books.get(i));
            }
        }
        return booksFound;
    }

    private void printBookNames(ArrayList<Book> books) {
        for (int i = 0; i < books.size(); i++)
            System.out.println(i+1 + " " + books.get(i).getTitle());
    }

    public void selectBook(Account loggedInPerson, ArrayList<Book> books) {
        while (true) {
            printBookNames(books);
            System.out.println("Select a book to see description/option or press 0 to exit");
            int bookChoice = userIntSelection();
            if (bookChoice == 0)
                return;

            try {
                System.out.println(books.get(bookChoice-1));
            } catch (Exception e) {
                System.out.println("This book do not exist");
                continue;
            }
            bookMethods(loggedInPerson, books.get(bookChoice-1));
        }
    }

    private void bookMethods (Account loggedInPerson, Book book) {
        while (true) {
            System.out.println("What do you want to do?");
            if (loggedInPerson instanceof User) {
                System.out.println("[1] Rent the book");
            }

            System.out.println("[0] Return to menu");
            String answer = scanner.nextLine();

            if (loggedInPerson instanceof User) {
                switch (answer) {
                    case "1":
                        User person = (User)loggedInPerson;
                        book.setAvailable(false);
                        person.getBorrowedBooks().add(book);
                        return;
                    case "0":
                        return;
                    default:
                        System.out.println("Sorry wrong input!");
                        break;
                }
            } else {
                if (answer.equals("0"))
                    return;
                else
                    System.out.println("Sorry wrong input!");
            }
        }
    }
}