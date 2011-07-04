<head>
<meta name='layout' content='main' />
<title>Student Control Panel | Lab Assistant</title>
</head>
<body>
<h2><g:message code='cp.student.courses.heading' /></h2>
<p><g:message code='cp.student.courses.size' args='${[courseCount]}'/></p>
<div id='cpCourseList'>
<ul>
<g:each in='${studentcourses}' var='course'>
	<li><g:link controller='course' action='list' id='${studentcourses.course.id}'>${studentcourses.course.name}</g:link></li>
</g:each>
</ul>
<div id='cpCourseList'></div>
</body>
