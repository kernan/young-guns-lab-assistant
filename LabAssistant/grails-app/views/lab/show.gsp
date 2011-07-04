<head>
	<meta name='layout' content='main' />
	<title>Lab Detail | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='labs.reports.heading' args='${[lab.name]}' /></h2>
	<br />
	<g:each in='${lab.teams}' var='team'>
		<h4>${team.name}</h4>
		<ul>
		<g:each in='${team.students}' var='student'>
			<li>${student.name}</li>
		</g:each>
		</ul>
	</g:each>
</body>