package com.mycompany.sso.servlet;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sso/view/member")
public class ViewMemberServlet extends BaseServlet {
    
    protected void doHandler(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/sso/view_member.jsp");
        req.setAttribute("username", username);
        req.setAttribute("members", getMember(username));
        rd.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandler(req, resp);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doHandler(req, resp);
    }
    
}
