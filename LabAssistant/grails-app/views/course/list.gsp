<html>
    <head>
        <title>List of Courses</title>
        <meta name="layout" content="main" />
        
    </head>
   	<body>
   		<!-- List out courses, in the future, give option for joining one. -->
   		<g:each in="${courseList}" var="course">
   			<p>${course.name}</p>
		</g:each>
   		
   		<%-- If the user is an instructor or admin give them the right to make a course. --%>
   		<%-- g:if test="${session.role == 'admin'}" --%> 
	   		<g:link controller="course" action="create">Create Course</g:link>
	   	<%-- </g:if> --%>
   	</body>
</html>