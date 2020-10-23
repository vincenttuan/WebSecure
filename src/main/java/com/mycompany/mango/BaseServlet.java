package com.mycompany.mango;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Map;
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
