package com.mycompany.websecure.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HelloServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String path = "/WEB-INF/jsp/hello.jsp";
        RequestDispatcher rd = getServletContext().getRequestDispatcher(path);
        rd.forward(req, resp);
    }
    
}
