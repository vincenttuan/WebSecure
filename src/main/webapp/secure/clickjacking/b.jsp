<%@page import="java.util.Date"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
    //response.setHeader("X-Frame-Options", "DENY");
    response.setHeader("X-Frame-Options", "SAMEORIGIN");
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>b.JSP Page</title>
    </head>
    <body>
        <h1>b.jsp</h1>
        <h1><%=new Date() %></h1>
    </body>
</html>
