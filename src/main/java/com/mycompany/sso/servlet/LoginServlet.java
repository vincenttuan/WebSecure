package com.mycompany.sso.servlet;

import com.mycompany.sso.PasswordRegex;
import com.mycompany.sso.SHA2;
import com.mycompany.sso.Salt;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
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
        
        boolean check = verifyCaptcha(grr);
        if(!check) {
            resp.getWriter().print("grr invalid");
            return;
        }
        
        check = login(username, password);
        if(!check) {
            resp.getWriter().print("login error");
            return;
        }
        
        //resp.getWriter().print("login ok");
        List<Map<String, Object>> members = getMember(username);
        
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/jsp/sso/view_member.jsp"); 
        req.setAttribute("username", username);
        req.setAttribute("members", members);
        rd.forward(req, resp);
    }
    
}
