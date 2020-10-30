package com.mycompany.sso.servlet;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        // 是否已經登入過 ?
        Set<String> usernames = (Set<String>)getServletContext().getAttribute("usernames");
        boolean flag = usernames.stream().filter(u -> u.equals(username)).findAny().isPresent();
        if(flag) {
            resp.getWriter().print(username + " already login !");
            return;
        }
        // 加入 usernames 集合中
        usernames.add(username);
        
        // 加入 session
        HttpSession session = req.getSession(true);
        session.setAttribute("username", username);
        session.setAttribute("member", getMember(username));
        //resp.getWriter().print("login ok");
        resp.sendRedirect("/WebSecure/sso/view/member?username=" + username);
    }
    
}
