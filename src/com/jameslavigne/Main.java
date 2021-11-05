package com.jameslavigne;

import java.text.DecimalFormat;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = Menu.getScanner();
        UserDao userDao = UserDaoFactory.getUserDao();
        CategoryDao catDao = CategoryDaoFactory.getCategoryDao();
        BookDao bookDao = BookDaoFactory.getBookDao();
        OrderDao orderDao = OrderDaoFactory.getOrderDao();
        OrderItemDao orderItemDao = OrderItemDaoFactory.getOrderItemDao();
        List<OrderItem> cart = new LinkedList<>();
        DecimalFormat df = Menu.df;

        while (true) {
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
                    if (userDao.validCredentials(username, password)) {
                        System.out.println(Menu.ANSI_CYAN + "Successful Login!" + Menu.ANSI_NORMAL);
                        //successful login
                        //get user info in userCurr
                        User currUser = userDao.getUserFromUsername(username);
                        if (currUser == null) {
                            System.out.println("Could Not Receive User Info");
                            break;
                        }

                        boolean browsingCategoryMenu = true;
                        while (browsingCategoryMenu) {
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
                                while (browsingBookMenu) {
                                    List<Book> books = bookDao.getBooksByCategory(currCategory.getCatId());
                                    Menu.printBookMenu(books);
                                    int numBook = 0;
                                    if (books != null) {
                                        numBook = books.size();
                                    }
                                    int bookMenuChoice = Menu.getMenuInput(numBook + 3);
                                    if (bookMenuChoice > 0 && bookMenuChoice <= numBook) {
                                        //chose a book
                                        Book currBook = books.get(bookMenuChoice - 1);
                                        Menu.printBookInfo(currBook);
                                        //Buy or Cancel Menu
                                        System.out.println("Choose an action below:");
                                        System.out.println("1. Buy");
                                        System.out.println("2. Cancel");
                                        int buyChoice = Menu.getMenuInput(2);
                                        if (buyChoice == 1) {
                                            //add book to cart
                                            addToCart(currBook, cart);
                                        }

                                    } else if (bookMenuChoice == numBook + 1) {
                                        //View Cart
                                        if (cart.size() == 0) {
                                            System.out.println(Menu.ANSI_CYAN + "Cart is Currently Empty." + Menu.ANSI_NORMAL);
                                        } else {
                                            System.out.println("Current Cart:");
                                            double cartTotal = 0;
                                            System.out.println(Menu.ANSI_CYAN + "---------------------------------");
                                            for (OrderItem oi : cart) {
                                                System.out.println(oi.getQuantity() + "x " + oi.getTitle() + " - $" + df.format(oi.getPrice()));
                                                cartTotal += oi.getPrice();
                                            }
                                            System.out.println("Cart Total: $" + df.format(cartTotal));
                                            System.out.println("---------------------------------" + Menu.ANSI_NORMAL);
                                            //checkout
                                            System.out.println("1. Checkout");
                                            System.out.println("2. Cancel");
                                            int checkoutChoice = Menu.getMenuInput(2);
                                            if (checkoutChoice == 1) {
                                                //add order
                                                int orderNum = orderDao.createOrder(currUser.getUserId());
                                                //set order items (order_id)
                                                for(OrderItem oi : cart){
                                                    oi.setOrderId(orderNum);
                                                }
                                                for(OrderItem oi : cart){
                                                    //add orderItem
                                                    orderItemDao.addOrderItem(oi);
                                                }
                                                //clear cart
                                                cart.clear();
                                            }
                                        }

                                    } else if (bookMenuChoice == numBook + 2) {
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
                        System.out.println(Menu.ANSI_RED + "Invalid Login Credentials" + Menu.ANSI_NORMAL);
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

    static void addToCart(Book book, List<OrderItem> cart) {
        boolean previousOccurrence = false;
        for (OrderItem oi : cart) {
            if (oi.getBookId() == book.getBookId()) {
                //add to quantity
                oi.incrementQuantity();
                oi.addToPrice(book.getPrice());
                previousOccurrence = true;
            }
        }

        //add new order item to cart
        if (previousOccurrence == false) {
            OrderItem orderItem = new OrderItem();
            orderItem.setBookId(book.getBookId());
            orderItem.setQuantity(1);
            orderItem.setPrice(book.getPrice());
            orderItem.setTitle(book.getTitle());
            cart.add(orderItem);
        }

        System.out.println(Menu.ANSI_CYAN + book.getTitle() + " by " + book.getAuthor() + " Added to Cart." + Menu.ANSI_NORMAL);
    }
}
