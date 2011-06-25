<html>
    <head>
        <title>Create a Course</title>
        <meta name="layout" content="main" />
        
    </head>
   	<body>
   		<g:form name="newCourse" url="[controller:'course',action:'save']">
   			<g:textField name="name">Name: </g:textField>
   			<g:actionSubmit action='save' value="Submit" />
   		
   		</g:form>
   	</body>
</html>