<html>
    <head>
        <title>All Courses | Lab Assistant</title>
        <meta name="layout" content="main" />
        
    </head>
   	<body>
   		<h2><g:message code="courses.list.heading" /></h2>
   		<!-- List out courses, in the future, give option for joining one. -->
   		<table>
   			<tr>
   				<td><b><g:message code="courses.table.heading.name" /></b></td>
   				<td><b><g:message code="courses.table.heading.instructor" /></b></td>
   				<td><b><g:message code="courses.table.heading.students" /></b></td>
   				<td><b><g:message code="courses.table.heading.labs" /></b></td>
   			</tr>
   			<g:each in="${courseList}" var="course">
   			<tr>
   				<td>${course.name}</td>
   				<td>${course.instructor.name}</td>
   				<td>${course.students?.size() ?: 0}</td>
   				<td>${course.labs?.size() ?: 0}</td>
   			</tr>
		</g:each>
   		</table>
   		
   		<%-- If the user is an instructor or admin give them the right to make a course. --%>
   		<sec:ifAnyGranted roles="ADMINISTRATOR,INSTRUCTOR">
	   		<p><g:link controller="course" action="create"><g:message code="cp.admin.courses.add" /></g:link></p>
	   	</sec:ifAnyGranted>
   	</body>
</html>