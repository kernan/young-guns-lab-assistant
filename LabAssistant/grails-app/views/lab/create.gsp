<head>
    <title>Create Lab | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2><g:message code="labs.create.heading" /></h2>
    <div id="form">
    	<g:form name='newLab' url='[controller:"lab",action:"save"]'>
			<table>
				<tr><td><label for='type'><g:message code='labs.create.type' /></label>
				<g:select name='type' from='${["Individual", "Student Select", "Random Select"]}' /></td></tr>
				<tr><td><label for='name'>Name<g:textField name="name" /></label>
				<tr><td><label for='startDate'>Start Date<g:datePicker name="startDate" precision="minute"
					years="${(1900 + (new Date().year))..(1901 + (new Date().year))}"/>
				</label></td></tr>
				<tr><td><label for='endDate'>End Date<g:datePicker name="endDate" precision="minute"
					years="${(1900 + (new Date().year))..(1901 + (new Date().year))}"/>
				</label></td></tr>
				<tr><td><g:actionSubmit action="save" value="${message(code: 'labs.create.submit')}" /></td></tr>
			</table>
		</g:form>
	</div>
</body>