<head>
    <title>Create Course | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
	<h2><g:message code="courses.create.heading" /></h2>
    <g:form name="newCourse" url="[controller:'course',action:'save']">
   	<p><label for="name">Name</label>
   	<g:textField name="name" /></p>
   	<p><g:actionSubmit action="save" value="${message(code: 'courses.create.submit')}" /></p>
   	</g:form>
</body>