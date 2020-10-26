<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<%
    response.setHeader("X-XSS-Protection", "1; mode=block");
    response.setHeader("Content-Security-Policy", "script-src 'self' 'nonce-1234'");
%>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mango</title>
        <script nonce="1234">
            alert('芒果團購');
        </script>
    </head>
    <body style="padding: 20px">
        <form class="pure-form" method="post" action="/WebSecure/mango/buy">
            <table>
                <tr>
                    <td valign="top"><img border=0 width="300" src="/WebSecure/mango/mango.jpg" /></td>
                    <td valign="top">
                        <fieldset>
                            <legend>Mango</legend>
                            <input type="text" placeholder="持卡人姓名" name="myName" value="${cookie.myName.value}" /><p />
                            <input type="text" placeholder="信用卡資料" name="cardNo" value="${cookie.cardNo.value}" /><p />
                            <input type="number" placeholder="數量" name="amount" /><p />
                            <button type="submit" class="pure-button pure-button-primary">Buy</button>
                        </fieldset>
                    </td>
                    <td valign="top">
                        <fieldset>
                            <legend>備註</legend>
                            <textarea placeholder="備註資訊" rows="7" name="memo"></textarea><p />
                        </fieldset>
                    </td>
                </tr>
            </table>
        </form>
        ${message}
        <table class="pure-table pure-table-bordered">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>MYNAME</th>
                    <th>AMOUNT</th>
                    <th>MEMO</th>
                    <th>TS</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="map" items="${list}">
                <tr>
                    <c:forEach var="m" items="${map}">
                        <td>
                            ${m.value}
                        </td>
                    </c:forEach>    
                </tr>
                </c:forEach>
            </tbody>
        </table>                
    </body>
</html>
