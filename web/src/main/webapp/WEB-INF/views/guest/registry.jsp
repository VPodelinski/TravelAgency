<h1>Fill in the registration fields for registration.</h1>
<form method="POST" action="registration" style="margin-top: 15px">
    <fieldset>

        <div>
            <input id="name" name="name" type="text">
            <span>Enter name</span>
        </div>
        <div>
            <input id="surname" name="surname" type="text">
            <span>Enter surname</span>
        </div>
        <div>
            <input id="email" name="email" type="text">
            <span>Enter e-mail</span>
        </div>
        <div>
            <input id="password" name="password" type="password" placeholder="" class="form-control input-md">
            <span>Enter password</span>
        </div>
        <div>
            <label for="role">
                <input type="checkbox" name="role" id="role" value="admin">
                Travel agent
            </label>
        </div>
        </div>
        <div>
            <div>
                <button id="buttonRegistry" name="buttonRegistry">Registry</button>
                <a href="home" style="margin-left: 25px;">Back</a>
            </div>
        </div>
        <div align="center">
            <h3>${operationMessage}<br></h3>

            <h3>${errorUserExists}<br/></h3>
        </div>
    </fieldset>
</form>