<html>
<head>
    <meta http-equiv="Content-type" content="text/html; charset=utf-8">
    <meta name="layout" content="main">
    <title>gTunes Welcome</title>
    <g:javascript library="jquery"></g:javascript>
</head>
<body>
    <div class="message notice">
        <h1>Your online music store and storage service!</h1>
        <p>Manage your own library, browse music and purchase new tracks as they become available</p>
    </div>
    <g:if test="${!session?.user}">

        <div class="colset clearfix">
            <div class="left">
                <h1>Need an account?</h1>
                <p class="legend"><g:link controller="user" action="register">Signup now</g:link> to start your own personal Music collection!</p>
                <g:link controller="user" action="register" class="btn">Signup now</g:link>
            </div>
            <div class="right">
                <h1>Already a member?</h1>
                <p class="legend">Log in to your gTunes account</p>
                <g:form name="loginForm" url="[controller:'user',action:'login']" class="form">
                    <div class="input">
                        <g:textField required="true" placeholder="Username" name="login" value="${fieldValue(bean:loginCmd, field:'login')}" />
                        <g:hasErrors  bean="${loginCmd}" field="login">
                            <p class="error"><g:fieldError bean="${loginCmd}" field="login" /></p>
                        </g:hasErrors>
                    </div>
                    <div class="input">
                        <g:passwordField required="true" placeholder="Password" name="password" />
                        <g:hasErrors  bean="${loginCmd}" field="password">
                            <p class="error"><g:fieldError bean="${loginCmd}" field="password" /></p>
                        </g:hasErrors>
                    </div>
                    <div class="submit">
                        <input type="submit" value="Login" class="btn" />
                    </div>
                </g:form>
            </div>
        </div>

    </g:if>

</body>
</html>
