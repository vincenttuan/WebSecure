package com.mycompany.singlesignon;

import java.io.IOException;
import java.util.Set;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/singlesignon/login")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        final String username = req.getParameter("username");
        if(username == null) {
            resp.getWriter().print("please input username");
            return;
        }
        // 是否有登入過 ?
        Set<String> loginUsernames = (Set<String>)getServletContext().getAttribute("loginUsernames");
        boolean hasLogin = loginUsernames.stream().filter(s -> s.equals(username)).findAny().isPresent();
        if(hasLogin) {
            resp.getWriter().print(username + " already login !");
        } else {
            HttpSession session = req.getSession();
            session.setAttribute("username", username);
            loginUsernames.add(username); // 加入到 Set<String> 集合中
            resp.getWriter().print(username + " login OK !");
        }
                
    }
    
}