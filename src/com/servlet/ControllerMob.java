/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.servlet;

import com.dao.CrudDao;
import java.util.HashMap;
import com.dao.DaoMobile;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author user
 */
public class ControllerMob {

    private static final long serialVersionUID = 1L;
    private HashMap<String, Object> JSONROOT = new HashMap<String, Object>();

    private DaoMobile dao;

    public ControllerMob() {
        dao = new DaoMobile();
    }

    protected void doGet(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        doPost(request, response);
    }

    protected void doPost(HttpServletRequest request,
            HttpServletResponse response) throws ServletException, IOException {
        
        String action = request.getParameter("action");
        if (action != null) {
			try {
                            
                            
                            } catch (Exception ex) {
				
				//response.getWriter().print(error);
			}
                        }

    }
}
