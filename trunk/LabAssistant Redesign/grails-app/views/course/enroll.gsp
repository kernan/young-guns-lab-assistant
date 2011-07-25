<head>
	<meta name='layout' content='main' />
	<title>Enroll in a Course | Lab Assistant</title>
</head>
<body>
	<h2><g:message code="courses.enroll.heading" /></h2>
	<g:form name='courseEnroll' url='[controller:"course",action:"enrollStudent"]'>
		<table>
			<tr><td><label for='course'><g:message code='courses.enroll.label' /></label>
			<g:select name='course' from='${courses}' optionKey='id' optionValue='name' /></td></tr>
			<tr><td><g:actionSubmit action="enrollStudent" value="${message(code: 'courses.join.submit')}" /></td></tr>
		</table>
	</g:form>
</body>