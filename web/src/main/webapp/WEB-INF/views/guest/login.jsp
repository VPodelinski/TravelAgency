<form class="form-horizontal" method="post" action="login" style="margin-top: 10%">
    <input type="hidden" name="command" value="login">
    <fieldset>
        <div align="center">
            <h3>${errorLoginOrPassword}<br/></h3>

            <h3>${operationMessage} <br></h3>
        </div>
        <!-- Text input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="login">Login</label>

            <div class="col-md-4">
                <input id="login" name="login" type="text" placeholder="" class="form-control input-md">
                <span class="help-block">Enter login</span>
            </div>
        </div>

        <!-- Password input-->
        <div class="form-group">
            <label class="col-md-4 control-label" for="password">Password</label>

            <div class="col-md-4">
                <input id="password" name="password" type="password" placeholder="" class="form-control input-md">
                <span class="help-block">Enter password</span>
            </div>
        </div>

        <!-- Button (Double) -->
        <div class="form-group">
            <label class="col-md-4 control-label" for="buttonLogin"></label>

            <div class="col-md-8">
                <button id="buttonLogin" name="buttonLogin" class="btn btn-success">Login</button>
                <a href="registration" style="margin-left: 25px;">Registry</a>
            </div>
        </div>

    </fieldset>
</form>
