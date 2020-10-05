package com.mycompany.singlesignon;

import java.util.LinkedHashSet;
import java.util.Set;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener
public class LoginApplicationListener implements ServletContextListener{
    private Set<String> loginUsernames;
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loginUsernames = new LinkedHashSet<>();
        sce.getServletContext().setAttribute("loginUsernames", loginUsernames);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        loginUsernames = null;
    }
    
}
