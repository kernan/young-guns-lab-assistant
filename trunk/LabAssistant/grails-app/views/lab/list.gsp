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
            </tr>
            <g:each in="${labList}" var="lab">
            <tr>
                <td>${lab.name}</td>
            </tr>
        </g:each>
        </table>
        <!-- check user permissions -->
        <sec:ifAnyGranted roles="ADMINISTRATOR,INSTRUCTOR">
            <p><g:link controller="lab" action="create"><g:message code="cp.admin.labs.add" /></g:link></p>
        </sec:ifAnyGranted>
    </body>
</html>