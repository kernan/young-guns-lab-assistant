<head>
	<title>Add User | Lab Assistant</title>
	<meta name='layout' content='main' />
</head>
<body>
	<h2><g:message code='users.create.heading' /></h2>
	<div id='form'>
		<g:form name='newUserType' url='[controller:"user",action:"selectType"]'>
			<table>
				<tr><td><label for='type'><g:message code='labs.create.type' /></label>
				<g:select name='type' from='${["Administrator", "Instructor", "Student"]}' /></td></tr>
				<tr><td><input type='submit' value='${message(code: "default.next.label")}' /></td></tr>
			</table>
		</g:form>
	</div>
</body>