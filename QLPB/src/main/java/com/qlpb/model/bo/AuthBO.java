package com.qlpb.model.bo;

import com.qlpb.model.dao.AuthDAO;

public class AuthBO {
    private final AuthDAO authDAO;
    public AuthBO() {
        this.authDAO=new AuthDAO();
    }

    public boolean isValidUser(String username,String password){
        return authDAO.authenticateUser(username, password);
    }
}