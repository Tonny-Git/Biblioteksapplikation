package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private ArrayList<Account> admins = new ArrayList<>();
    private ArrayList<Account> users = new ArrayList<>();
    private BookList bookList = new BookList();
    private Scanner scanner = new Scanner(System.in);

    public Program() {
        startMenu();
    }

    private void startMenu() {
        while (true) {
            System.out.println("Welcome, please log in.");
            System.out.println("[1] Log in as user ");
            System.out.println("[2] Log in as admin");
            System.out.println("[3] Create a new account");
            System.out.println("[0] Exit");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    logIn(true);
                    break;
                case "2":
                    logIn(false);
                    break;
                case "3":
                    createAccount();
                    break;
                case "0":
                    System.out.println("Thank you for using this app!");
                    return;
                default:
                    System.out.println("This is wrong input! Please try again!");
                    System.out.println("Press enter to continue. . .");
                    scanner.nextLine();
                    break;
            }
        }
    }

    private void createAccount() {
        boolean loop = true;
        boolean createAdminAccount = false;
        while (loop) {
            System.out.println("[1] For user account");
            System.out.println("[2] For admin account");
            System.out.println("[0] Go back to menu");

            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    createAdminAccount = false;
                    loop = false;
                    break;
                case "2":
                    createAdminAccount = true;
                    loop = false;
                    break;
                case "0":
                    return;
                default:
                    System.out.println("This is wrong input! Please try again!");
                    break;
            }
        }

        System.out.println("Enter account name");
        String name = scanner.nextLine();
        String password;
        while (true) {
            System.out.println("Enter password");
            password = scanner.nextLine();
            if (password.equals("") || password.equals("0")) {
                System.out.println("The password can't be empty or 0");
                continue;
            }
            break;
        }

        if (createAdminAccount) {
            admins.add(new Admin(name, password));
        } else {
            users.add(new User(name, password));
        }
    }

    private void logIn(boolean isUser) {
        ArrayList<Account> accounts;
        if (isUser) {
            accounts = users;
        } else {
            accounts = admins;
        }

        if (accounts == null || accounts.size() == 0) {
            System.out.println("There are no accounts. Please create an account.");
            return;
        }

        int answer;
        while (true) {
            System.out.println("Please select your account");
            for (int i = 0; i < accounts.size(); i++) {
                System.out.printf("[%d] %s%n", i+1, accounts.get(i).getUsername());
            }

            try {
                answer = Integer.parseInt(scanner.nextLine());
                accounts.get(answer-1); //Kollar om indexet finns i arrayen.
            } catch (Exception e) {
                System.out.println("Wrong input! try again!");
                continue;
            }
            break;
        }

        while (true) {
            System.out.println("Please enter password for " + accounts.get(answer-1).getUsername() + " or press 0 to exit");
            String passwordAnswer = scanner.nextLine();
            if (accounts.get(answer-1).isPasswordCorrect(passwordAnswer)) {
                loggedInMenu(accounts.get(answer-1));
                return;
            } else if (passwordAnswer.equals("0")) {
                return;
            } else {
                System.out.println("Wrong password! Please try again");
            }
        }
    }

    //Jobbar vidare med denna metoden!
    private void loggedInMenu(Account loggedInPerson) {
        while (true) {


            if (loggedInPerson instanceof Admin) {
                System.out.println("is admin");
                // adminOptions();
            } else {
                System.out.println("is user");
                // userOptions();
            }
            System.out.println("[0] Return to start menu");

            String answer = scanner.nextLine();

            switch (answer) {
                case "0":
                    return;
                default:
                    System.out.println("This is not a valid input!");
                    break;
            }
        }



    }
}
