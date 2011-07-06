<head>
    <title>Create Team | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2><g:message code="teams.create.heading" args="${[lab.name]}" /></h2>
    <div id="form">
    	<g:form name='newTeam' url='[controller:"team",action:"save"]'>
    		<input type='hidden' name='lab' value='${lab.id}' />
			<table>
				<tr><td><label for='name'><g:message code='teams.create.name' /></label><g:textField name="name" /></td></tr>
				<tr><td><g:actionSubmit action="save" value="${message(code: 'teams.create.submit')}" /></td></tr>
			</table>
		</g:form>
	</div>
</body>