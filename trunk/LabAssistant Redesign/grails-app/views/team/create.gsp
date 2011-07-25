<head>
	<meta name='layout' content='main' />
	<title>Create Team | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='teams.create.lab.heading' /></h2>
	<g:form name='labSelect' url='[controller:"team",action:"createTeam"]'>
		<table>
			<tr><td><label for='lab'><g:message code='teams.create.lab' /></label>
			<g:select name='lab' from='${labList}' optionKey='id' optionValue='name' /></td></tr>
			<tr><td><g:actionSubmit action="createTeam" value="${message(code: 'default.next.label')}" /></td></tr>
		</table>
	</g:form>
</body>