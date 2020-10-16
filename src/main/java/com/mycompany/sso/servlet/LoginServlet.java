package com.mycompany.sso.servlet;

import com.mycompany.sso.PasswordRegex;
import com.mycompany.sso.SHA2;
import com.mycompany.sso.Salt;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sso/login")
public class LoginServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setContentType("text/html;charset=utf-8");
        String grr = req.getParameter("g-recaptcha-response");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        
        int salt = Salt.getSalt();
        String password_hash_salt = SHA2.getSHA256(password, salt);
        boolean check = verifyCaptcha(grr);
        
        resp.getWriter().print(check + "<br>");
        resp.getWriter().print("username: " + username + " <br>");
        resp.getWriter().print("password: check=" + PasswordRegex.check(password) + ", salt=" + salt + ", hash=" + password_hash_salt + " <br>");
    }
    
}
