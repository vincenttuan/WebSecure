package com.mycompany.mango;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mycompany.sso.SHA2;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;
import javax.servlet.http.HttpServlet;
import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.handlers.MapListHandler;

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
    
    protected List<Map<String, Object>> query() {
        String sql = "SELECT ID, MYNAME, CARDNO, AMOUNT, MEMO, TS FROM MANGO";
        try(PreparedStatement stmt = conn.prepareStatement(sql);) {
            ResultSet rs = stmt.executeQuery();
            BasicRowProcessor convert = new BasicRowProcessor();
            MapListHandler handler = new MapListHandler(convert);
            return handler.handle(rs);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
    
}
