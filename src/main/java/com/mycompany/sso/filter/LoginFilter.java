package com.mycompany.sso.filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/sso/view/*")
public class LoginFilter extends HttpFilter {

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession(false);
        if(session == null || session.getAttribute("username") == null) {
            //res.sendRedirect("/WebSecure/sso/login_form.jsp");
            RequestDispatcher rd = req.getServletContext().getRequestDispatcher("/sso/login_form.jsp");
            rd.forward(req, res);
            return;
        }
        //String referer = req.getHeader("referer");
        
        chain.doFilter(req, res);
    }
    
}
