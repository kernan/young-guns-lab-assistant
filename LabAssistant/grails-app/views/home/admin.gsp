<head>
	<meta name='layout' content='main' />
	<title>Admin Control Panel | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='cp.admin.users.heading' /></h2>
	<p><g:message code='cp.admin.users.size' args='${[activeUsers, admins, instructors, students]}' /></p>
	<p><g:message code='cp.admin.users.locked' args='${[lockedUsers]}' /></p>
	<p><g:link controller='user' action='list'><g:message code='cp.admin.users.view' /></g:link>&nbsp;&nbsp;&nbsp;
	<g:link controller='user' action='create'><g:message code='cp.admin.users.add' /></g:link>&nbsp;&nbsp;&nbsp;
	<g:link controller='user' action='reset'><g:message code='cp.admin.users.unlock' /></g:link></p>
	<br />
	<h2><g:message code='cp.admin.courses.heading' /></h2>
	<p><g:message code='cp.admin.courses.size' args='${[courses]}' /></p>
	<g:if test='${adminCourseCount > 0}'>
		<h4><g:message code='cp.admin.courses.teach' /></h4>
		<div id='cpCourseList'>
		<ul style='list-style: none'>
		<g:each in='${adminCourses}' var='course'>
			<li><g:link controller='course' action='list' id='${course.id}'>${course.name}</g:link></li>
		</g:each>
		</ul>
		</div>
	</g:if>
	<p><g:link controller='course' action='list'><g:message code='cp.admin.courses.view' /></g:link>&nbsp;&nbsp;&nbsp;
	<g:link controller='course' action='create'><g:message code='cp.admin.courses.add' /></g:link></p>
</body>