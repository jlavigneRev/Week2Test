package com.jameslavigne;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = Menu.getScanner();
        UserDao userDao = UserDaoFactory.getUserDao();
        CategoryDao catDao = CategoryDaoFactory.getCategoryDao();
        BookDao bookDao = BookDaoFactory.getBookDao();

        while(true) {
            // Login Menu
            System.out.println("Welcome to the bookstore!");
            System.out.println("Enter an Option Below:");
            System.out.println("1. Login");
            System.out.println("2. Create a New Account");
            System.out.println("3. Exit");
            int loginChoice = Menu.getMenuInput(3);

            String username;
            String password;

            switch (loginChoice) {
                case 1:
                    System.out.println("Please Enter Credentials Below:");
                    username = Menu.getUsernameInput();
                    password = Menu.getPasswordInput();
                    if(userDao.validCredentials(username, password)){
                        System.out.println("Successful Login!");
                        //successful login
                        //get user info in userCurr
                        User currUser = userDao.getUserFromUsername(username);
                        if(currUser == null) {
                            System.out.println("Could Not Receive User Info");
                            break;
                        }
                        //create Cart

                        boolean browsingCategoryMenu = true;
                        while(browsingCategoryMenu) {
                            //Category Menu
                            List<Category> categories = catDao.getCategories();
                            Menu.printCategoryMenu(categories);
                            int numCategory;
                            if (categories != null) {
                                numCategory = categories.size();
                            } else {
                                numCategory = 0;
                            }
                            int categoryMenuChoice = Menu.getMenuInput(numCategory + 2);
                            if (categoryMenuChoice > 0 && categoryMenuChoice <= numCategory) {
                                //chose category
                                Category currCategory = categories.get(categoryMenuChoice - 1);
                                System.out.println("Chose Category : " + currCategory.getName());

                                //book menu
                                boolean browsingBookMenu = true;
                                while(browsingBookMenu) {
                                    List<Book> books = bookDao.getBooksByCategory(currCategory.getCatId());
                                    Menu.printBookMenu(books);
                                    int numBook = 0;
                                    if(books != null){
                                        numBook = books.size();
                                    }
                                    int bookMenuChoice = Menu.getMenuInput(numBook + 3);
                                    if (bookMenuChoice > 0 && bookMenuChoice <= numBook){
                                        //chose a book
                                        Book currBook = books.get(bookMenuChoice - 1);
                                        System.out.println("Chose Book : " + currBook.getTitle());

                                    } else if(bookMenuChoice == numBook + 1){
                                        //View Cart
                                        System.out.println("View Cart Here");
                                    } else if(bookMenuChoice == numBook + 2){
                                        //Back
                                        browsingBookMenu = false;
                                    } else {
                                        //Exit
                                        Menu.exit();
                                    }
                                }



                            } else if (categoryMenuChoice == numCategory + 1) {
                                //Logout
                                browsingCategoryMenu = false;
                            } else {
                                //Exit
                                Menu.exit();
                            }
                        }
                    } else {
                        //invalid login
                        System.out.println("Invalid Login Credentials");
                    }
                    break;
                case 2:
                    System.out.println("Complete the input fields below:");
                    username = Menu.getUsernameInput();
                    password = Menu.getPasswordInput();
                    String name = Menu.getNameInput();
                    String address = Menu.getAddressInput();
                    userDao.addUser(username, password, name, address);
                    break;
                case 3:
                    Menu.exit();
                    break;
            }
        }
    }
}
