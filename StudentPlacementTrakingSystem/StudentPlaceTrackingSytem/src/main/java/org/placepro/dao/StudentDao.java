package org.placepro.dao;

import java.util.List;

import org.placepro.model.Users;

public interface StudentDao {
    void addStudent(Users student);
    void deleteStudent(int id);
    List<Users> getAllStudents();

    // Add these so your implementation compiles
    Users getStudentById(int id);
    void updateStudent(Users student);
    void updateStatus(int id, String status);
    List<Users> searchUsers(String course, Double percentage, String skill);
}
