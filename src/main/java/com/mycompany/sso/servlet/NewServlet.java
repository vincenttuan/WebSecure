package com.mycompany.sso.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/sso/new")
public class NewServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String grr = req.getParameter("g-recaptcha-response");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String email = req.getParameter("email");
        int money = Integer.parseInt(req.getParameter("money"));
        
        // 確認 grr
        if(!verifyCaptcha(grr)) {
            resp.getWriter().print("grr false");
            return;
        }
        
        // 確認 password
        String regex = "(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[!@#$%^&*()]).{8,64}";
        if(!password.matches(regex)) {
            resp.getWriter().print("password invalid");
            return;
        }
        
        // 建立 member
        boolean check = saveMember(username, password, email, money);
        resp.getWriter().print(check);
    }
    
}
