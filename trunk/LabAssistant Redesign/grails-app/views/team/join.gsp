<head>
	<meta name='layout' content='main' />
	<title>Create Team | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='teams.join.team.heading' /></h2>
	<g:form name='teamSelect' url='[controller:"team",action:"join"]'>
		<table>
			<tr><td><label for='team'><g:message code='teams.join.team' /></label>
			<g:select name='team' from='${teamList}' optionKey='id' optionValue='name' /></td></tr>
			<tr><td><g:actionSubmit action="joinTeam" value="${message(code: 'teams.join.submit')}" /></td></tr>
		</table>
	</g:form>
</body>