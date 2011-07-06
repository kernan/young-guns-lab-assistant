<head>
	<meta name='layout' content='main' />
	<title>Select Lab | Lab Assistant</title>
</head>
<body>
	<h2><g:message code="teams.join.selectLab" /></h2>
	<g:form name='labSelect' url='[controller:"team",action:"join"]'>
		<table>
			<tr><td><label for='lab'><g:message code='teams.join.selectlab' /></label>
			<g:select name='lab' from='${labs}' optionKey='id' optionValue='name' /></td></tr>
			<tr><td><g:actionSubmit action="join" value="${message(code: 'default.next.label')}" /></td></tr>
		</table>
	</g:form>
</body>