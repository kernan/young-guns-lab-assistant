<html>
    <head>
        <title>All Labs | Lab Assistant</title>
        <meta name="layout" content="main" />
        
    </head>
    <body>
        <h2><g:message code="labs.list.heading" /></h2>
        <table>
            <tr>
                <td><b><g:message code="labs.table.heading.name" /></b></td>
                <td><b><g:message code="labs.table.heading.startDate" /></b>
                <td><b><g:message code="labs.table.heading.endDate" /></b>
            </tr>
            <g:each in="${labList}" var="lab">
            <tr>
            	<g:set var="labId" value="${lab.id}" />
                <td><g:link controller="lab" action="show" params="[lab: labId]">${lab.name}</g:link></td>
                <td>${lab.startDate}</td>
                <td>${lab.endDate}</td>
            </tr>
        </g:each>
        </table>
        <!-- check user permissions -->
        <sec:ifAnyGranted roles="ROLE_ADMINISTRATOR,ROLE_INSTRUCTOR">
            <p><g:link controller="lab" action="create"><g:message code="cp.instructor.labs.add" /></g:link></p>
        </sec:ifAnyGranted>
    </body>
</html>