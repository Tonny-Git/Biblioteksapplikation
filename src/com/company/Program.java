package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Program {
    private ArrayList<Admin> admins = new ArrayList<>();
    private ArrayList<User> users = new ArrayList<>();
    Scanner scanner = new Scanner(System.in);

    public Program() {
        startMenu();
    }

    public void startMenu() {
        while (true) {
            System.out.println("Welcome, please log in.");
            System.out.println("[1] Log in as user ");
            System.out.println("[2] Log in as admin");
            System.out.println("[3] Create a new account");
            System.out.println("[0] Exit");
            String answer = scanner.nextLine();

            switch (answer) {
                case "1":
                    //
                    break;
                case "2":
                    //
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
        System.out.println("Enter password");
        String password = scanner.nextLine();

        if (createAdminAccount) {
            admins.add(new Admin(name, password));
        } else {
            users.add(new User(name, password));
        }
    }
}
