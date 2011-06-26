<head>
<meta name='layout' content='main' />
<title>Instructor Control Panel | Lab Assistant</title>
</head>
<body>
<h2><g:message code='cp.instructor.courses.heading' /></h2>
<p><g:message code='cp.instructor.courses.size' args='${[courseCount]}'/></p>
<div id='cpCourseList'>
<ul>
<g:each in='${courses}' var='course'>
	<li><g:link controller='course' action='list' id='${course.id}'>${course.name}</g:link></li>
</g:each>
</ul>
</div>
<p><g:link controller='course' action='list'><g:message code='cp.instructor.courses.manage' /></g:link></p>
<p><g:link controller='course' action='create'><g:message code='cp.instructor.courses.add' /></g:link></p>
</body>