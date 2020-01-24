package com.company;

public class Main {

    public static void main(String[] args) {
        Program program = (Program) FileUtility.loadObject("biblioteksapplikation.ser");
        if (program == null) {
            program = new Program();
        }
        program.startMenu();
        FileUtility.saveObject("biblioteksapplikation.ser", program);
    }
}
