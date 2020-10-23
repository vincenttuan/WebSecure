package com.mycompany.sso.listener;

import java.util.Set;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

@WebListener
public class LogoutSessionListener implements HttpSessionListener {

    @Override
    public void sessionCreated(HttpSessionEvent se) {
        HttpSessionListener.super.sessionCreated(se); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se) {
        String username = se.getSession().getAttribute("username") + "";
        Set<String> usernames = (Set<String>)se.getSession().getServletContext().getAttribute("usernames");
        usernames.remove(username);
    }
    
}
