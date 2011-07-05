<head>
	<meta name='layout' content='main' />
	<title>Lab Detail | Lab Assistant</title>
</head>
<body>
	<h2><g:message code='labs.reports.heading' args='${[lab.name]}' /></h2>
	<br />
	<!-- display list of students -->
	<g:ifIndividualLab lab="${lab}">
		<g:each in='${lab.teams}' var='team'>
			<!-- <h4>${team.name}</h4> -->
			<ul>
			<g:each in='${team.students}' var='student'>
				<li>${student.name}</li>
			</g:each>
			</ul>
		</g:each>
	</g:ifIndividualLab>
	<!-- display list of random teams -->
	<g:ifRandomLab lab="${lab}">
		<g:each in='${lab.teams}' var='team'>
			<h4>${team.name}</h4>
			<ul>
			<g:each in='${team.students}' var='student'>
				<li>${student.name}</li>
			</g:each>
			</ul>
		</g:each>
	</g:ifRandomLab>
	<!-- display team list with join and create options -->
	<g:ifSelfSelectLab lab="${lab}">
		<g:each in='${lab.teams}' var='team'>
			<h4>${team.name} </h4>
			<ul>
			<g:each in='${team.students}' var='student'>
				<li>${student.name}</li>
			</g:each>
			</ul>
		</g:each>
		<p><g:link controller="team" action="join"><g:message code="cp.student.teams.join" /></g:link></p>
		<p><g:link controller="team" action="create"><g:message code="cp.student.teams.add" /></g:link></p>
	</g:ifSelfSelectLab>
</body>