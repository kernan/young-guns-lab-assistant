<head>
    <title>Create Lab | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2><g:message code="labs.create.heading" args="${[course.name]}" /></h2>
    <div id="form">
    	<g:form name='newLab' url='[controller:"lab",action:"save"]'>
    		<input type='hidden' name='course' value='${course.id}' />
			<table>
				<tr><td><label for='name'><g:message code='labs.create.name' /></label><g:textField name="name" /></td></tr>
				<tr><td><label for='type'><g:message code='labs.create.type' /></label>
				<g:select name='type' from='${["Individual", "Student Select", "Random Select"]}' /></td></tr>
				<tr><td><label for='teamSize'>Team Size</label><g:textField name='teamSize' /></td></tr>
				<tr><td><label for='startDate'>Start Date</label><g:datePicker name="startDate" precision="minute"
					years="${(1900 + (new Date().year))..(1901 + (new Date().year))}"/>
				</td></tr>
				<tr><td><label for="endDate">End Date</label><g:datePicker name="endDate" precision="minute"
					years="${(1900 + (new Date().year))..(1901 + (new Date().year))}"/>
				</td></tr>
				<tr><td><g:actionSubmit action="save" value="${message(code: 'labs.create.submit')}" /></td></tr>
			</table>
		</g:form>
	</div>
</body>