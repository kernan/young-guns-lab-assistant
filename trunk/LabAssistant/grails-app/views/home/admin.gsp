<head>
	<meta name='layout' content='main' />
	<title>Administrative Control Panel | Lab Assistant</title>
</head>
<body>
	<h2>User Management</h2>
	<p><g:message code='cp.admin.users.size' args='${[activeUsers, admins, instructors, students]}' /></p>
	<p><g:message code='cp.admin.users.locked' args='${[lockedUsers]}' /></p>
	<p><g:link controller='users' action='show'><g:message code='cp.admin.users.view' /></g:link>&nbsp;&nbsp;&nbsp;
	<g:link controller='users' action='create'><g:message code='cp.admin.users.add' /></g:link>&nbsp;&nbsp;&nbsp;
	<g:link controller='users' action='reset'><g:message code='cp.admin.users.unlock' /></g:link></p>
	<br />
	<h2>Course Management</h2>
	<p>More stuff here...</p>
</body>