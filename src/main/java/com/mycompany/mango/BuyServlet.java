package com.mycompany.mango;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        } else {
            req.setAttribute("message", "新增失敗");
        }
        rd.forward(req, resp);
    }
    
}
