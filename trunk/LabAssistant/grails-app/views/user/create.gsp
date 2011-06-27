<head>
<meta name='layout' content='main' />
<title>Add User | Lab Assistant</title>
</head>
<body>
<h2><g:message code='users.create.heading' /></h2>
<g:form name='newUserType' url='[controller:"user",action:"selectType"]'>
<p><label for='type'><g:message code='users.create.type' /></label>
<g:select name='type' from='${["Administrator", "Instructor", "Student"]}' /></p>
<p><input type='submit' value='${message(code: "default.next.label")}' /></p>
</g:form>
</body>