<head>
<meta name='layout' content='main' />
<title>All Users | Lab Assistant</title>
</head>
<body>
<h2><g:message code='users.list.heading' /></h2>
<p><g:message code='users.list.size' args='${[userCount]}' /></p>
<p><g:message code='users.list.admins' args='${[adminCount]}' /> (<g:link controller='user' action='listAdmins'><g:message code='default.view.label' /></g:link>)</p>
<p><g:message code='users.list.instructors' args='${[instructorCount]}' /> (<g:link controller='user' action='listInstructors'><g:message code='default.view.label' /></g:link>)</p>
<p><g:message code='users.list.students' args='${[studentCount]}' /> (<g:link controller='user' action='listStudents'><g:message code='default.view.label' /></g:link>)</p>
<br />
<table>
	<tr>
		<td><b><g:message code='users.table.heading.name' /></b></td>
		<td><b><g:message code='users.table.heading.username' /></b></td>
		<td><b><g:message code='users.table.heading.type' /></b></td>
	</tr>
	<g:each in='${users}' var='user'>
	<tr>
		<td>${user.name}</td>
		<td>${user.username}</td>
		<td>${user.getAuthorities().contains(adminRole) ? "Administrator" : user.getAuthorities().contains(instructorRole) ? "Instructor" : "Student" }</td>
	</tr>
	</g:each>
</table>
<p><g:link controller='user' action='create'><g:message code='cp.admin.users.add' /></g:link></p>
</body>