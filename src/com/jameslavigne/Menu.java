package com.jameslavigne;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Scanner;

public class Menu {
    public static DecimalFormat df = new DecimalFormat("0.00");
    //console colors
    public static final String ANSI_NORMAL = "\033[0m";
    public static final String ANSI_RED = "\033[0;31m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    private static Scanner scanner;

    public static Scanner getScanner(){
        if(scanner == null){
            scanner = new Scanner(System.in);
        }
        return scanner;
    }

    public static int getMenuInput(int max) {
        while (true) {
            try {
                String inputString = scanner.nextLine();
                int input = Integer.parseInt(inputString);
                if (input >= 1 && input <= max) {
                    //valid number
                    return input;
                }
            } catch (NumberFormatException e) {
                //e.printStackTrace();
            }
            System.out.println(ANSI_RED + "Please, Enter a valid selection from (1-" + max + ")" + ANSI_NORMAL);
        }
    }

    public static String getUsernameInput() {
        System.out.print("Enter your username: ");
        String username = scanner.nextLine();
        return username;
    }

    public static String getPasswordInput() {
        System.out.print("Enter your password: ");
        String password = scanner.nextLine();
        return password;
    }

    public static String getNameInput() {
        System.out.print("Enter your name:");
        String name = scanner.nextLine();
        return name;

    }

    public static String getAddressInput() {
        System.out.print("Enter your address:");
        String address = scanner.nextLine();
        return address;

    }

    public static void printCategoryMenu(List<Category> categories){
        System.out.println("********************************************");
        int listSize = 0;
        if (categories != null) {
            listSize = categories.size();
        }
        System.out.println("Please choose an option listed below. (1-" + (listSize + 2) + "):");
        for (int i = 0; i < listSize; i++) {
            System.out.println("Enter " + (i + 1) + ". View " + categories.get(i).getName() + " Books");
        }
        System.out.println("Enter " + (listSize + 1) + ". Logout");
        System.out.println("Enter " + (listSize + 2) + ". Exit");
    }

    public static void printBookMenu(List<Book> books){
        System.out.println("********************************************");
        int listSize = 0;
        if (books != null) {
            listSize = books.size();
        }
        System.out.println("Please choose an option listed below. (1-" + (listSize + 3) + "):");
        for (int i = 0; i < listSize; i++) {
            System.out.println("Enter " + (i + 1) + ". View Book (" + ANSI_CYAN + books.get(i).getTitle() + " by "
                    + books.get(i).getAuthor() + " - ISBN:" + books.get(i).getIsbn() + ANSI_NORMAL + ")");
        }
        System.out.println("Enter " + (listSize + 1) + ". View Cart");
        System.out.println("Enter " + (listSize + 2) + ". Back");
        System.out.println("Enter " + (listSize + 3) + ". Exit");
    }

    public static void printBookInfo(Book book){
        System.out.println(ANSI_CYAN + "--------------------------");
        System.out.println("Title: " + book.getTitle());
        System.out.println("Author: " + book.getAuthor());
        System.out.println("ISBN: " + book.getIsbn());
        System.out.println("Price: $" + book.getPrice());
        System.out.println("--------------------------" + ANSI_NORMAL);
    }

    public static void exit(){
        System.out.println(ANSI_CYAN + "Exiting...");
        System.out.println("Bye" + ANSI_NORMAL);
        System.exit(0);
    }
}