<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
    <head>
        <link rel="stylesheet" href="https://unpkg.com/purecss@2.0.3/build/pure-min.css">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login Form</title>
        <script src="https://www.google.com/recaptcha/api.js?render=6Lf_JtYZAAAAAEIE8pIOhd_yIwvaV2gTrsf7vhPX"></script>
        <script>
            function init() {
                grecaptcha.ready(function () {
                    grecaptcha.execute('6Lf_JtYZAAAAAEIE8pIOhd_yIwvaV2gTrsf7vhPX', {action: 'submit'}).then(function (token) {
                        document.getElementById('g-recaptcha-response').value = token;
                    });
                });
            }
        </script>
    </head>
    <body style="padding: 20px" onload="init()">

        <form class="pure-form">
            <fieldset>
                <legend>Login Form</legend>
                <input type="text" id="g-recaptcha-response" name="g-recaptcha-response"><p />
                <input type="text" placeholder="Username" name="username" /><p />
                <input type="password" placeholder="Password" name="password" /><p />
                <button type="submit" class="pure-button pure-button-primary">Sign in</button>
            </fieldset>
        </form>

    </body>
</html>
