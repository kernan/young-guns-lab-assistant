<%@ page import="org.codehaus.groovy.grails.commons.ConfigurationHolder" %>

<!DOCTYPE html>
<html>
    <head>
        <title><g:layoutTitle default="Lab Assistant" /></title>
        <link rel="stylesheet" href="${resource(dir:'css',file:'main.css')}" />
        <link rel="shortcut icon" href="${resource(dir:'images',file:'favicon.ico')}" type="image/x-icon" />
        <g:layoutHead />
        <g:javascript library="application" />
    </head>
    <body>
    	<header>
    		<div id="header">
				<div id="welcome">
					<sec:ifNotLoggedIn>
						<p>Welcome! Please <g:link controller="login" action="auth">sign in</g:link> to use the Lab Assistant.</p>
					</sec:ifNotLoggedIn>
					<sec:ifLoggedIn>
						<p>Welcome, <sec:username />! (<g:link controller="logout">sign out</g:link>)</p>
					</sec:ifLoggedIn>
				</div>
				<div id="logo">
					<a href="${ConfigurationHolder.getConfig().getProperty('grails.serverURL')}">
					<img src="${resource(dir:'images',file:'logo.png')}" alt="Lab Assistant" border="0" /></a>
				</div>
				<div id="menuContainer">
				<div id="menu">
					<ul>
						<li><h2><g:message code="menu.courses.heading" /></h2>
							<ul>
								<sec:ifLoggedIn>
								<li><g:link controller="course" action="list"><g:message code="menu.courses.view" /></g:link></li>
								</sec:ifLoggedIn>
								<sec:ifAnyGranted roles="INSTRUCTOR,ADMINISTRATOR">
								<li><g:link controller="course" action="create"><g:message code="menu.courses.create" /></g:link></li>
								</sec:ifAnyGranted>
								<sec:ifAnyGranted roles="STUDENT,ADMINISTRATOR">
								<li><g:link controller="course" action="enroll"><g:message code="menu.courses.enroll" /></g:link></li>
								</sec:ifAnyGranted>
							</ul>
						</li>
					</ul>
					<ul>
						<li><h2><g:message code="menu.labs.heading" /></h2>
							<ul>
								<sec:ifLoggedIn>
								<li><g:link controller="lab" action="list"><g:message code="menu.labs.view" /></g:link></li>
								</sec:ifLoggedIn>
								<sec:ifAnyGranted roles="INSTRUCTOR,ADMINISTRATOR">
								<li><g:link controller="lab" action="create"><g:message code="menu.labs.create" /></g:link></li>
								</sec:ifAnyGranted>
							</ul>
						</li>
					</ul>
					<ul>
						<li><h2><g:message code="menu.teams.heading" /></h2>
							<ul>
								<sec:ifLoggedIn>
								<li><g:link controller="team" action="list"><g:message code="menu.teams.view" /></g:link></li>
								</sec:ifLoggedIn>
								<sec:ifAnyGranted roles="STUDENT,ADMINISTRATOR">
								<li><g:link controller="team" action="join"><g:message code="menu.teams.join" /></g:link></li>
								</sec:ifAnyGranted>
							</ul>
						</li>
					</ul>
					<sec:ifAllGranted roles="ADMINISTRATOR">
					<ul>
						<li><h2><g:message code="menu.administer.heading" /></h2>
							<ul>
								<li><g:link controller="home" action="index"><g:message code="menu.administer.cp" /></g:link></li>
								<li><g:link controller="user" action="list"><g:message code="menu.administer.users.view" /></g:link></li>
								<li><g:link controller="user" action="reset"><g:message code="menu.administer.reset" /></g:link></li>
								<li><g:link controller="user" action="suspend"><g:message code="menu.administer.suspend" /></g:link></li>
							</ul>
						</li>
					</ul>
					</sec:ifAllGranted>
				</div>
				</div>
			</div>
    	</header>
        <div id="spinner" class="spinner" style="display:none;">
            <img src="${resource(dir:'images',file:'spinner.gif')}" alt="${message(code:'spinner.alt',default:'Loading...')}" />
        </div>
        <g:layoutBody />
        <!-- TODO: need a standard footer for each page -->
    </body>
</html>