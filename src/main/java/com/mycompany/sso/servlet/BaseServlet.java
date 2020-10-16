package com.mycompany.sso.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.sso.SHA2;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.http.HttpServlet;

public class BaseServlet extends HttpServlet {
    // 建立 connection
    private static Connection conn;
    static {
        try {
            Class.forName("org.apache.derby.jdbc.ClientDriver");
            conn = DriverManager.getConnection("jdbc:derby://localhost:1527/security", "app", "app");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
    
    protected boolean saveMember(String username, String password, String email, int money) {
        int salt = new Random().nextInt(10_0000);
        password = SHA2.getSHA256(password, salt);
        int bonus = new Random().nextInt(10_0000);
        String sql_1 = "INSERT INTO Member(username, password, salt, email) values ('" + username + "', '" + password + "', " + salt + ", '" + email + "')";
        String sql_2 = "INSERT INTO Salary(username, money, bonus) values ('" + username + "', " + money + ", " + bonus + ")";
        try(Statement stmt = conn.createStatement();) {
            conn.setAutoCommit(false);
            stmt.clearBatch();
            stmt.addBatch(sql_1);
            stmt.addBatch(sql_2);
            int[] rowscount = stmt.executeBatch();
            conn.commit();
            if(Arrays.stream(rowscount).sum() == 2) {
                return true;
            } else {
                conn.rollback();
            }
        } catch(Exception e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        return false;
    }
    
    protected boolean verifyCaptcha(String grr) throws MalformedURLException, IOException {
        String secret = "6Lf_JtYZAAAAAKYtDPaFi-d8gWe-M7L3TejxS5Pa";
        String remoteip = "localhost";
        String path = "https://www.google.com/recaptcha/api/siteverify?secret=%s&response=%s&remoteip=%s";
        path = String.format(path, secret, grr, remoteip);
        String json = new Scanner(new URL(path).openStream(), "UTF-8").useDelimiter("\\A").next();
        // { "success": true, "challenge_ts": "2020-10-16T11:51:14Z", "hostname": "localhost", "score": 0.9, "action": "submit" }
        Gson gson = new Gson();
        Map<String, Object> map = gson.fromJson(json, new TypeToken<Map<String, Object>>(){}.getType());
        System.out.println(json);
        System.out.println(map);
        boolean success = Boolean.parseBoolean(map.get("success").toString());
        if(success) {
            double score = Double.parseDouble(map.get("score").toString());
            boolean check = success && score >= 0.5;
            return check;
        }
        return false;
    }
}
