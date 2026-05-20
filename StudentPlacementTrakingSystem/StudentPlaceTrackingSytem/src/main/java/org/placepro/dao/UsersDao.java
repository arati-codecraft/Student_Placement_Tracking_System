package org.placepro.dao;

import java.util.List;

import org.placepro.model.Users;

public interface UsersDao {
    void addUser(Users u);
    void updateUser(Users u);
    void deleteUser(int id);
    Users getUserById(int id);
    List<Users> getAllUsers();
    List<Users> searchUsers(String course, Double percentage, String skill);
    void updateUserStatus(int id, String status);
    List<Users> getAllStudents();
}

