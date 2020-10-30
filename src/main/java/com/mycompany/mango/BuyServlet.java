package com.mycompany.mango;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mango/buy")
public class BuyServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        List<Map<String, Object>> member = (List<Map<String, Object>>)session.getAttribute("member");
        String myName = member.get(0).get("USERNAME") + "";
        String cardNo = member.get(0).get("CARDNO") + "";
        String amount = req.getParameter("amount");
        String memo = req.getParameter("memo");
        boolean check = append(myName, cardNo, Integer.parseInt(amount), memo);
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/mango/input");
        if(check) {
            req.setAttribute("message", "新增成功");
            // 寫入 Cookie
            Cookie cookie_myName = new Cookie("myName", myName);
            //cookie_myName.setHttpOnly(true);
            //cookie_myName.setSecure(true);
            Cookie cookie_cardNo = new Cookie("cardNo", cardNo);
            //cookie_cardNo.setHttpOnly(true);
            //cookie_cardNo.setSecure(true);
            resp.addCookie(cookie_myName);
            resp.addCookie(cookie_cardNo);
        } else {
            req.setAttribute("message", "新增失敗");
        }
        rd.forward(req, resp);
    }
    
}
