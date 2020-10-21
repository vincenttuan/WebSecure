<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>View  Salary</title>
    </head>
    <body style="padding: 20px">
        <h1>Hello ${username} 您好 !</h1>
        <table class="pure-table pure-table-bordered">
            <thead>
                <tr>
                    <th>username</th>
                    <th>money</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="map" items="${salary}">
                <tr>
                    <c:forEach var="m" items="${map}">
                        <c:choose>
                            <c:when test="${m.key == 'USERNAME'}">
                                <td><a href="#">${m.value}</a></td>
                            </c:when>
                            <c:when test="${m.key == 'MONEY'}">
                                <td>${m.value}</td>
                            </c:when>
                        </c:choose>
                    </c:forEach>
                </tr>
                </c:forEach>
            </tbody>
        </table>
    </body>
</html>
