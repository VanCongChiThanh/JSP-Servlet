package com.mvc_example.controller;

import com.mvc_example.model.bean.Wife;
import com.mvc_example.model.bo.CheckLoginBO;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

@WebServlet("/CheckLoginServlet")
public class CheckLoginServlet extends HttpServlet{
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String destination=null;
        String username=req.getParameter("username");
        String password=req.getParameter("password");
        CheckLoginBO checkLoginBO=new CheckLoginBO();
        ArrayList<Wife> wifeList=null;
        try {
            if(checkLoginBO.isValidUser(username,password)){
                wifeList=checkLoginBO.getWifeList(username);
                req.setAttribute("wifeList", wifeList);
                destination = "/mvc_example/welcome.jsp";
                RequestDispatcher rd= getServletContext().getRequestDispatcher(destination);
                rd.forward(req, resp);
            }
            else{
                destination="/mvc_example/login.jsp";
                req.setAttribute("error", "Invalid username or password");
                RequestDispatcher rd= getServletContext().getRequestDispatcher(destination);
                rd.forward(req, resp);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}