package com.mycompany.sso.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Scanner;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    
    protected boolean verifyCaptcha(String grr) throws MalformedURLException, IOException {
        String secret = "6Lf_JtYZAAAAAKYtDPaFi-d8gWe-M7L3TejxS5Pa";
        String remoteip = "localhost";
        String path = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";
        path = String.format(path, secret, grr, remoteip);
        String json = new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        // { "success": true, "challenge_ts": "2020-10-16T11:51:14Z", "hostname": "localhost", "score": 0.9, "action": "submit" }
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
        boolean check = Boolean.parseBoolean(map.get("success").toString());
        return check;
    }
}
