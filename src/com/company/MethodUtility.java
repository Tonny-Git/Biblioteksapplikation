package com.company;

import java.util.Scanner;

public class MethodUtility {
    public static Scanner scanner = new Scanner(System.in);

    private MethodUtility() {
    }

    public static String accountAndPasswordCheck(String type) {
        String answer;
        while (true) {
            System.out.println("Enter " + type);
            answer = scanner.nextLine();
            if (answer.equals("") || answer.length() < 4) {
                System.out.println("The " + type + " can't be less then 4 letters");
                continue;
            }
            break;
        }
        return answer;
    }

    public static int intSelectionArray(int arrayListSize) {
        int answer;
        while (true) {
            try {
                answer = Integer.parseInt(scanner.nextLine());
            } catch (Exception e) {
                System.out.println("Wrong input. Please try again!");
                continue;
            }
            if (answer <= arrayListSize && answer >= 0) {
                return answer-1;
            } else
                System.out.println("Number out of bounds! Please try again!");
        }
    }

    public static void printErrorMessage() {
        System.out.println("This is wrong input! Please try again!");
        System.out.println("Press enter to continue. . .");
        scanner.nextLine();
    }
}
