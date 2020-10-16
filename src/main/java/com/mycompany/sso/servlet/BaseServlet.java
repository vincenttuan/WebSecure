package com.mycompany.sso.servlet;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    
    protected String verifyCaptcha(String grr) throws MalformedURLException, IOException {
        String secret = "6Lf_JtYZAAAAAKYtDPaFi-d8gWe-M7L3TejxS5Pa";
        String remoteip = "localhost";
        String path = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";
        path = String.format(path, secret, grr, remoteip);
        String json = new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        return json;
    }
}
