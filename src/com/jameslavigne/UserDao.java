package com.jameslavigne;

public interface UserDao {
    void addUser(String username, String password, String name, String address);

    boolean validCredentials(String username, String password);

    User getUserFromUsername(String username);
}
