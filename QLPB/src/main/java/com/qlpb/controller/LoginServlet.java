package com.qlpb.controller;

import com.qlpb.model.bean.Admin;
import com.qlpb.model.bo.AuthBO;
import com.qlpb.model.dao.AuthDAO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String uname = request.getParameter("username");
        String pword = request.getParameter("password");

        Admin user = new Admin(uname, pword);
        AuthBO authBO = new AuthBO();
        boolean status = authBO.isValidUser(uname, pword);

        if (status) {
            HttpSession session = request.getSession();
            session.setAttribute("username", uname);
            response.sendRedirect("index.jsp");
        } else {
            request.setAttribute("error", "Sai tên đăng nhập hoặc mật khẩu!");
            RequestDispatcher rd = request.getRequestDispatcher("login.jsp");
            rd.forward(request, response);
        }
    }

}