package com.mvc_example.model.bo;

import com.mvc_example.model.bean.Wife;
import com.mvc_example.model.dao.CheckLoginDAO;

import java.sql.Array;
import java.sql.SQLException;
import java.util.ArrayList;

public class CheckLoginBO {
    CheckLoginDAO checkLoginDAO=new CheckLoginDAO();
    public boolean isValidUser(String user, String password) throws ClassNotFoundException, SQLException{
        return checkLoginDAO.isExistingUser(user, password);
    }
    public ArrayList<Wife> getWifeList(String username){
        return checkLoginDAO.getWifeList(username);
    }
}