<head>
	<meta name='layout' content='main' />
	<title>Add Administrator | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='users.create.admin.heading' /></h2>
	<div id='form'>
		<g:form name='newAdmin' url='[controller:"user", action:"save"]'>
			<input type='hidden' name='type' value='admin' />
			<table>
				<tr><td><label for='name'><g:message code='users.create.name' /></label><g:textField name='name' /></td></tr>
				<tr><td><label for='username'><g:message code='users.create.username' /></label><g:textField name='username' /></td></tr>
				<tr><td><label for='password1'><g:message code='users.create.password' /></label><g:passwordField name='password1' /></td></tr>
				<tr><td><label for='password2'><g:message code='users.create.password2' /></label><g:passwordField name='password2' /></td></tr>
				<tr><td><label for='enabled'><g:message code='users.create.enabled' /></label><g:checkBox name='enabled' value='${true}' /></td></tr>
				<tr><td><input type='submit' value='${message(code: "users.create.submit")}' /></td></tr>
			</table>
		</g:form>
	</div>
</body>