<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <script>
            function init() {
                var data = document.getElementById('myframe').contentWindow.document.body.innerHTML;
                alert(data);
            }
        </script>
    </head>
    <body onload="init()">
        <h1>A.jsp</h1>
        <iframe id="myframe" src="https://www.pcschool.com.tw"></iframe>
    </body>
</html>
