<head>
<meta name='layout' content='main' />
<title>Add Administrator | Lab Assistant</title>
</head>
<body>
<h2><g:message code='users.create.admin.heading' /></h2>
<g:form name='newAdmin' url='[controller:"user", action:"save"]'>
<input type='hidden' name='type' value='admin' />
<p><label for='name'><g:message code='users.create.name' /></label>
<g:textField name='name' /></p>
<p><label for='username'><g:message code='users.create.username' /></label>
<g:textField name='username' /></p>
<p><label for='password1'><g:message code='users.create.password' /></label>
<g:passwordField name='password1' /></p>
<p><label for='password2'><g:message code='users.create.password2' /></label>
<g:passwordField name='password2' /></p>
<p><label for='enabled'><g:message code='users.create.enabled' /></label>
<g:checkBox name='enabled' value='${true}' /></p>
<p><input type='submit' value='${message(code: "users.create.submit")}' /></p>
</g:form>
</body>