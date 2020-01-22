package com.company;

import java.util.Scanner;

public class MethodUtility {
    private static Scanner scanner = new Scanner(System.in);

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
}
