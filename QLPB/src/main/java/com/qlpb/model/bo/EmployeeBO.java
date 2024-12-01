package com.qlpb.model.bo;

import com.qlpb.model.bean.Employee;
import com.qlpb.model.dao.EmployeeDAO;

import java.util.List;

public class EmployeeBO {
    private EmployeeDAO employeeDAO;

    // Constructor
    public EmployeeBO() {
        employeeDAO = new EmployeeDAO();
    }
    public Employee getEmployeeById(String employeeId) {
        return employeeDAO.getEmployeeById(employeeId);
    }

    // Thêm nhân viên (có thể kiểm tra logic trước khi thêm)
    public boolean addEmployee(Employee employee) {
        if (employee == null || employee.getId().isEmpty() || employee.getName().isEmpty()) {
            System.err.println("Invalid employee data.");
            return false;
        }
        return employeeDAO.addEmployee(employee);
    }

    // Sửa thông tin nhân viên
    public boolean updateEmployee(Employee employee) {
        if (employee == null || employee.getId().isEmpty()) {
            System.err.println("Invalid employee ID.");
            return false;
        }
        return employeeDAO.updateEmployee(employee);
    }

    // Xóa nhân viên
    public boolean deleteEmployee(String id) {
        if (id == null || id.isEmpty()) {
            System.err.println("Invalid employee ID.");
            return false;
        }
        return employeeDAO.deleteEmployee(id);
    }

    // Lấy danh sách nhân viên theo phòng ban
    public List<Employee> getEmployeesByDepartment(String departmentID) {
        if (departmentID == null || departmentID.isEmpty()) {
            System.err.println("Invalid department ID.");
            return null;
        }
        return employeeDAO.getEmployeesByDepartment(departmentID);
    }

    // Tìm kiếm nhân viên theo tên
    public List<Employee> searchEmployeesByName(String name) {
        if (name == null || name.isEmpty()) {
            System.err.println("Invalid employee name.");
            return null;
        }
        return employeeDAO.searchEmployeesByName(name);
    }

    public List<Employee> getAllEmployees() {
        return employeeDAO.searchEmployeesByName("%");
    }
}