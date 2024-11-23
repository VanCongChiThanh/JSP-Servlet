package com.student_management.controller;

import com.student_management.model.bo.BO;
import com.student_management.model.dao.DAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/deleteServlet")
public class DeleteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        if (id != null && !id.isEmpty()) {
            BO bo=new BO();
            try {
                boolean result = bo.deleteStudent(id);
                if (result) {
                    response.sendRedirect("home.jsp");
                } else {
                    response.sendRedirect("home.jsp");
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}