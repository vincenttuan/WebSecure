package com.mycompany.mango;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mango/input")
public class InputServlet extends BaseServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<Map<String, Object>> list = query();
        RequestDispatcher rd = getServletContext().getRequestDispatcher("/WEB-INF/jsp/mango/input.jsp");
        req.setAttribute("list", list);
        rd.forward(req, resp);
    }
    
}
