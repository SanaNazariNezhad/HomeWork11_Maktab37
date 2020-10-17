package org.maktab.homework11_maktab37.repository;

import org.maktab.homework11_maktab37.model.User;

import java.util.List;

public interface IUserRepository {
    List<User> getUsers();
    User getUser(String username);
    void insertUser(User user);
}
