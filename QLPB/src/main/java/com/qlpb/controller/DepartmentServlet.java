package com.qlpb.controller;

import com.qlpb.model.bean.Department;
import com.qlpb.model.bo.DepartmentBO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.List;

@WebServlet("/departments")
public class DepartmentServlet extends HttpServlet {
    private DepartmentBO departmentBO;

    public DepartmentServlet() {
        departmentBO = new DepartmentBO();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            fetchDepartments(request, response);
            return;
        }
        switch (action) {
            case "addForm":
                showAddForm(request, response);
                break;
            case "editForm":
                showEditForm(request, response);
                break;
            default:
                fetchDepartments(request, response);
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
                addDepartment(request, response);
                break;
            case "update":
                updateDepartment(request, response);
                break;
            case "delete":
                deleteDepartment(request, response);
                break;
            default:
                response.sendError(HttpServletResponse.SC_NOT_FOUND, "Action not recognized");
                break;
        }
    }

    private void updateDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Department department = new Department(id, name, description);
        departmentBO.updateDepartment(department);
        response.sendRedirect("departments");
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response) {
        String id = request.getParameter("id");
        Department department = departmentBO.getDepartmentById(id);
        request.setAttribute("department", department);
        try {
            request.getRequestDispatcher("editDepartment.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void deleteDepartment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        departmentBO.deleteDepartment(id);
        response.sendRedirect("departments");
    }

    private void fetchDepartments(HttpServletRequest request, HttpServletResponse response) {
        System.out.println("Fetching departments");
        List<Department> departments = departmentBO.getAllDepartments();
        request.setAttribute("departments", departments);
        try {
            request.getRequestDispatcher("manageDepartments.jsp").forward(request, response);
        } catch (ServletException | IOException e) {
            e.printStackTrace();
        }
    }

    private void showAddForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            System.out.println("Forwarding to addDepartment.jsp");
            request.getRequestDispatcher("addDepartment.jsp").forward(request, response);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void addDepartment(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
        String id = request.getParameter("id");
        String name = request.getParameter("name");
        String description = request.getParameter("description");
        Department department = new Department(id, name, description);
        departmentBO.addDepartment(department);
        response.sendRedirect("departments");
    }
}