package com.mycompany.singlesignon;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/singlesignon/logout")
public class LogoutServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        resp.getWriter().print(session.getAttribute("username"));
        if(session == null) {
            resp.getWriter().print("logout error");
        } else {
            session.invalidate();
            resp.getWriter().print("logout OK");
        }
    }
    
}
