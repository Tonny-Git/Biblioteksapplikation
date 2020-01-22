package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private ArrayList<Account> admins = new ArrayList<>();
    private ArrayList<Account> users = new ArrayList<>();
    private BookList bookList = new BookList();
    private Scanner scanner = new Scanner(System.in);

    public Program() {
        users.add(new User("asdf", "1234"));
        users.add(new User("qwer", "1234"));
        users.add(new User("zxcv", "1234"));
        startMenu();
    }

    private void startMenu() {
        while (true) {
            System.out.println("Welcome, please log in.");
            System.out.println("[1] Log in");
            System.out.println("[2] Create a new account");
            System.out.println("[0] Exit");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    logIn();
                    break;
                case "2":
                    createAccount();
                    break;
                case "0":
                    System.out.println("Thank you for using this app!");
                    return;
                default:
                    MethodUtility.printErrorMessage();
                    break;
            }
        }
    }

    private void createAccount() {
        while (true) {
            System.out.println("[1] For user account");
            System.out.println("[2] For admin account");
            System.out.println("[0] Go back to menu");
            String answer = scanner.nextLine();

            String userName = "";
            String password = "";
            if (answer.equals("1") || answer.equals("2")) {
                userName = MethodUtility.accountAndPasswordCheck("name");
                password = MethodUtility.accountAndPasswordCheck("password");
            }

            switch (answer) {
                case "1":
                    users.add(new User(userName, password));
                    return;
                case "2":
                    admins.add(new Admin(userName, password));
                    return;
                case "0":
                    return;
                default:
                    System.out.println("This is wrong input! Please try again!");
                    break;
            }
        }
    }

    private void logIn() {
        ArrayList<Account> accounts = users;
        accounts.addAll(admins);

        if (accounts == null || accounts.size() == 0) {
            System.out.println("There are no accounts. Please create an account.");
            return;
        }

        System.out.println("Please select your account");
        for (int i = 0; i < accounts.size(); i++) {
            System.out.printf("[%d] %s%n", i+1, accounts.get(i).getUsername());
        }
        int answer = MethodUtility.intSelectionArray(accounts.size());
        Account account = accounts.get(answer);

        while (true) {
            System.out.println("Please enter password for " + account.getUsername() + " or press 0 to exit");
            String passwordAnswer = scanner.nextLine();
            if (account.isPasswordCorrect(passwordAnswer)) {
                if (account instanceof User)
                    loggedInUserMenu((User) account);
                else if (account instanceof Admin)
                    loggedInAdminMenu((Admin) account);
                return;
            } else if (passwordAnswer.equals("0")) {
                return;
            } else {
                System.out.println("Wrong password! Please try again");
            }
        }
    }

    private void loggedInUserMenu(User loggedInPerson) {
        while (true) {
            System.out.println("[1] See all books.");
            System.out.println("[2] Show your borrowed books.");
            System.out.println("[3] Search after book.");
            System.out.println("[4] Show all available books.");
            System.out.println("[0] Log out");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    bookList.seeAllBooks(loggedInPerson);
                    break;
                case "2":
                    User user = (User) loggedInPerson;
                    bookList.showAndReturnBorrowedBook(user);
                    break;
                case "3":
                    bookList.searchAfterBook(loggedInPerson);
                    break;
                case "4":
                    bookList.selectBook(loggedInPerson, bookList.searchThroughArray("ShowAllAvailableBooks"));
                    break;
                case "0":
                    return;
                default:
                    System.out.println("This is not a valid input!");
                    break;
            }
        }
    }

    //Fixa Remove book
    private void loggedInAdminMenu(Admin loggedInPerson) {
        while (true) {
            System.out.println("[1] See all books.");
            System.out.println("[2] Add a new book.");
            System.out.println("[3] Show all users");
            System.out.println("[4] Show all borrowed books");
            System.out.println("[5] Search after an user");
            System.out.println("[?] Remove a book"); //fixa senare
            System.out.println("[0] Log out");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    bookList.seeAllBooks(loggedInPerson);
                    break;
                case "2":
                    bookList.addNewBook();
                    break;
                case "3":
                    showAllUsers();
                    break;
                case "4":
                    bookList.selectBook(loggedInPerson, bookList.searchThroughArray("ShowAllBorrowedBooks"));
                    break;
                case "5":
                    searchAfterUser();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("This is not a valid input!");
                    break;
            }
        }
    }

    private void showAllUsers() {
        for (int i = 0; i < users.size(); i++) {
            System.out.println(i+1 + " " + users.get(i).getUsername());
        }
        selectUser();
    }

    private void selectUser() {
        while (true) {
            System.out.println("Select an user to see borrowed books or press 0 to exit");
            int answer;
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input! please try again!");
                continue;
            }

            if (answer ==  0) {
                return;
            }

            try {
                User user = (User) users.get(answer-1);
                booksBorrowedByUser(user);
            } catch (Exception e) {
                System.out.println("Wrong input! please try again!");
                continue;
            }
            return;
        }
    }

    private void booksBorrowedByUser(User user) {
        for (int i = 0; i < user.getBorrowedBooks().size(); i++) {
            System.out.println(i+1 + " " + user.getBorrowedBooks().get(i).getTitle());
        }
    }

    private void searchAfterUser() {
        System.out.println("Enter title or author of an book");
        String answer = scanner.nextLine().toLowerCase();
        User user = null;

        for (int i = 0; i < users.size(); i++) {
            if (answer.equals(users.get(i).getUsername())) {
                user = (User) users.get(i);
            }
        }

        if (user ==  null)
            System.out.println("No Books found");
        else
            booksBorrowedByUser(user);
    }
}
