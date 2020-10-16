package com.mycompany.sso.servlet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.sso.SHA2;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
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
            String url = "jdbc:derby://localhost:1527/security";
            String user = "app";
            String password = "app";
            conn = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
        }
    }
    
    protected boolean saveMember(String username, String password, String email, int money) {
        int salt = new Random().nextInt(10_0000);
        password = SHA2.getSHA256(password, salt);
        int bonus = new Random().nextInt(10_0000);
        
        String sql_1 = "INSERT INTO Member(username, password, salt, email) VALUES ('%s', '%s', %d, '%s')";
        String sql_2 = "INSERT INTO Salary(username, money, bonus) VALUES ('%s', %d, %d)";
        sql_1 = String.format(sql_1, username, password, salt, email);
        sql_2 = String.format(sql_2, username, money, bonus);
        
        try(Statement stmt = conn.createStatement()) {
            conn.setAutoCommit(false); // 不要自動 commit (提交給資料庫)
            stmt.clearBatch();
            stmt.addBatch(sql_1);
            stmt.addBatch(sql_2);
            int[] rowscount = stmt.executeBatch();
            conn.commit(); // 手動 commit
            
            if(Arrays.stream(rowscount).sum() == 2) {
                return true;
            } else {
                conn.rollback(); // 回復原狀
            }
            
        } catch (Exception e) {
            try {
                conn.rollback(); // 回復原狀
            } catch (Exception e2) {
                e2.printStackTrace();
            }
        } finally {
            try {
                conn.setAutoCommit(true); // 回復自動 commit
            } catch (Exception e3) {
                e3.printStackTrace();
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
        boolean success = Boolean.parseBoolean(map.get("success").toString());
        double score = Double.parseDouble(map.get("score").toString());
        boolean check = success && score >= 0.5;
        return check;
    }
}
