<h1>Enter the email and password for authentication.</h1>
<form method="post" action="login">
    <input type="hidden" name="command" value="login">
    <fieldset>
        <div align="center">
            <h3>${errorLoginOrPassword}<br/></h3>
            <h3>${operationMessage} <br></h3>
        </div>
        <!-- Text input-->
        <div>
            <div>
                <input id="email" name="email" type="text">
                <span >Enter email</span>
            </div>
        </div>
        <!-- Password input-->
        <div>
            <input id="password" name="password" type="password">
            <span> Enter password</span>
        </div>
        <!-- Button (Double) -->
        <div>
            <div>
                <button id="buttonLogin" name="buttonLogin">Login</button>
                <a href="registration">Registry</a>
            </div>
        </div>
    </fieldset>
</form>
