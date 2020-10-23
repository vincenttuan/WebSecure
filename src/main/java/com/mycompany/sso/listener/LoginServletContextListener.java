package com.mycompany.sso.listener;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LoginServletContextListener implements ServletContextListener {
    private Set<String> usernames;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        usernames = new LinkedHashSet<>();
        sce.getServletContext().setAttribute("usernames", usernames);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {

    }
    
}
