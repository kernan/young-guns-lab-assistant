<head>
    <title>Create Course | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
	<h2><g:message code="courses.create.heading" /></h2>
	<div id="form">
    	<g:form name="newCourse" url="[controller:'course',action:'save']">
    		<table>
    			<tr><td><label for="name">Name</label><g:textField name="name" /></td></tr>
   				<tr><td><g:actionSubmit action="save" value="${message(code: 'courses.create.submit')}" /></td></tr>
   			</table>
   		</g:form>
   	</div>
</body>