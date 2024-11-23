package com.student_management.model.bean;

public class Student {
    private String id;
    private String name;
    private String address;
    private String faculty;

    public Student(String id, String name, String address, String faculty) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.faculty = faculty;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}