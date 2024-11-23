package com.student_management.model.bo;

import com.student_management.model.bean.Student;
import com.student_management.model.dao.DAO;

import java.util.ArrayList;

public class BO {
    private DAO dao;

    public BO() {
        this.dao = new DAO();
    }

    public boolean addStudent(String id, String name, String address, String faculty) {
        return dao.addStudent(id, name, address, faculty);
    }

    public Student getStudentById(String id) {
        return dao.getStudentById(id);
    }

    public boolean updateStudent(String id, String name, String address, String faculty) {
        return dao.updateStudent(id, name, address, faculty);
    }

    public boolean deleteStudent(String id) {
        return dao.deleteStudent(id);
    }

    public ArrayList<Student> getAllStudents() {
        return dao.getAllStudents();
    }
    public boolean isValidUser(String username,String password){
        return dao.authenticateUser(username, password);
    }
}