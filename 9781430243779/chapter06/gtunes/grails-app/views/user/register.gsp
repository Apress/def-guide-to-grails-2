<html>
	<head>
		<meta http-equiv="Content-type" content="text/html; charset=utf-8">
		<meta name="layout" content="main">
		<title>gTunes Register</title>
		<g:javascript library="jquery"></g:javascript>
	</head>
	<body id="body">

        <div class="message notice">
            <h1>Registration</h1>
            <p>Complete the form below to create an account!</p>
        </div>

		<g:form action="register" name="registerForm" class="form form-horizontal">
			<div class="input clearfix">
				<label for="login">Login <span class="required">*</span></label>
				<g:textField required="true" name="login" value="${user?.login}" />
                <g:hasErrors  bean="${user}" field="login">
                    <p class="error"><g:fieldError bean="${user}" field="login" /></p>
                </g:hasErrors>
			</div>
			<div class="input clearfix">
				<label for="password">Password <span class="required">*</span></label>
				<g:passwordField required="true" name="password" value="${user?.password}" />
                <g:hasErrors  bean="${user}" field="password">
                    <p class="error"><g:fieldError bean="${user}" field="password" /></p>
                </g:hasErrors>
			</div>
            <div class="input clearfix">
				<label for="confirm">Confirm Password <span class="required">*</span></label>
				<g:passwordField required="true" name="confirm" value="${params?.confirm}" />
                <g:hasErrors  bean="${user}" field="confirm">
                    <p class="error"><g:fieldError bean="${user}" field="confirm" /></p>
                </g:hasErrors>
			</div>
			<div class="input clearfix">
				<label for="firstName">First Name <span class="required">*</span></label>
				<g:textField required="true" name="firstName" value="${user?.firstName}" />
                <g:hasErrors  bean="${user}" field="firstName">
                    <p class="error"><g:fieldError bean="${user}" field="firstName" /></p>
                </g:hasErrors>
			</div>			
			<div class="input clearfix">
				<label for="lastName">Last Name <span class="required">*</span></label>
				<g:textField required="true" name="lastName" value="${user?.lastName}" />
                <g:hasErrors  bean="${user}" field="lastName">
                    <p class="error"><g:fieldError bean="${user}" field="lastName" /></p>
                </g:hasErrors>
			</div>
            <div class="submit">
                <g:submitButton class="btn" name="register" value="Register" />
            </div>
		</g:form>
	</body>
	
</html>
