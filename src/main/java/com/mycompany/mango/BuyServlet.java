package com.mycompany.mango;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mango/buy")
public class BuyServlet extends BaseServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String myName = req.getParameter("myName");
        String cardNo = req.getParameter("cardNo");
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
