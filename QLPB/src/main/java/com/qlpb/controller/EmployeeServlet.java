package com.qlpb.controller;

import com.qlpb.model.bean.Employee;
import com.qlpb.model.bo.EmployeeBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/employees")
public class EmployeeServlet extends HttpServlet {
    private EmployeeBO employeeBO;

    public EmployeeServlet() {
        employeeBO = new EmployeeBO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            fetchEmployees(request, response);
            return;
        }
        switch (action) {
            case "addForm":
                showAddForm(request, response);
                break;
            case "editForm":
                showEditForm(request, response);
                break;
            case "viewByDepartment":
                viewByDepartment(request, response);
                break;
            case "viewbyName":
            default:
                fetchEmployees(request, response);
                break;
        }
    }


    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action == null) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Action is missing");
            return;
        }

        switch (action) {
            case "insert":
                addEmployee(request, response);
                break;
            case "update":
                updateEmployee(request, response);
                break;
            case "delete":
                deleteEmployee(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not recognized");
                break;
        }
    }
    private void viewByDepartment(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String departmentId = request.getParameter("id");
        List<Employee> employees = employeeBO.getEmployeesByDepartment(departmentId);
        request.setAttribute("employees", employees);
        request.getRequestDispatcher("manageEmployees.jsp").forward(request, response);
    }
    private void updateEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String departmentID=request.getParameter("departmentID");
        String address=request.getParameter("address");
        Employee employee=new Employee(id, name, departmentID, address);
        employeeBO.updateEmployee(employee);
        response.sendRedirect("employees");
    }
    private void showEditForm(HttpServletRequest request, HttpServletResponse response){
        String id=request.getParameter("id");
        Employee employee=employeeBO.getEmployeeById(id);
        request.setAttribute("employee", employee);
        try {
            request.getRequestDispatcher("editEmployee.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void deleteEmployee(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id=request.getParameter("id");
        employeeBO.deleteEmployee(id);
        response.sendRedirect("employees");
    }

    private void fetchEmployees(HttpServletRequest request, HttpServletResponse response){
        System.out.println("Fetching employees");
        List<Employee> employees=employeeBO.getAllEmployees();
        request.setAttribute("employees", employees);
        try {
            request.getRequestDispatcher("manageEmployees.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }
    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Forwarding to addEmployee.jsp");
            request.getRequestDispatcher("addEmployee.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    private void addEmployee(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id=request.getParameter("id");
        String name=request.getParameter("name");
        String departmentID=request.getParameter("departmentID");
        String address=request.getParameter("address");
        Employee employee=new Employee(id,name,departmentID,address);
        employeeBO.addEmployee(employee);
        response.sendRedirect("employees");
    }
}