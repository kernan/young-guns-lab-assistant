<head>
	<meta name='layout' content='main' />
	<title>Create Lab | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='labs.create.course.heading' /></h2>
	<g:form name='courseSelect' url='[controller:"lab",action:"createLab"]'>
		<table>
			<tr><td><label for='course'><g:message code='labs.create.courses' /></label>
			<g:select name='course' from='${courseList}' optionKey='id' optionValue='name' /></td></tr>
			<tr><td><g:actionSubmit action="createLab" value="${message(code: 'default.next.label')}" /></td></tr>
		</table>
	</g:form>
</body>