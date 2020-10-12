package com.mycompany.sso.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sso/login")
public class LoginServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String grr = req.getParameter("g-recaptcha-response");
        String json = verifyCaptcha(grr);
        resp.getWriter().print(json);
        resp.getWriter().print("username:");
        resp.getWriter().print("password:");
    }
    
}
