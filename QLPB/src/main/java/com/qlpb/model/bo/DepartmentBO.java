package com.qlpb.model.bo;

import com.qlpb.model.bean.Department;
import com.qlpb.model.dao.DepartmentDAO;

import java.util.List;

public class DepartmentBO {
    private DepartmentDAO departmentDAO;

    public DepartmentBO() {
        departmentDAO = new DepartmentDAO();
    }

    public List<Department> getAllDepartments() {
        return departmentDAO.getAllDepartments();
    }

    public Department getDepartmentById(String id) {
        return departmentDAO.getDepartmentById(id);
    }

    public void addDepartment(Department department) {
        departmentDAO.addDepartment(department);
    }

    public void updateDepartment(Department department) {
        departmentDAO.updateDepartment(department);
    }

    public void deleteDepartment(String id) {
        departmentDAO.deleteDepartment(id);
    }
}