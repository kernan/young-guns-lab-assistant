<head>
    <title>Create Course | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
	<h2><g:message code="courses.create.heading" /></h2>
	<div id="form">
    	<g:form name="newCourse" url="[controller:'course',action:'save']">
    		<table>
    			<tr><td><label for="name"><g:message code="courses.create.name" /></label><g:textField name="name" /></td></tr>
    			<sec:ifAllGranted roles="ROLE_ADMINISTRATOR">
    				<tr><td><label for="instructor"><g:message code="courses.create.instructor" /></label>
    				<g:select name="instructor" from="${instructors}" optionKey="id" optionValue="name" noSelection="${['THIS_GUY': userName]}"/></td></tr>
    			</sec:ifAllGranted>
   				<tr><td><g:actionSubmit action="save" value="${message(code: 'courses.create.submit')}" /></td></tr>
   			</table>
   		</g:form>
   	</div>
</body>