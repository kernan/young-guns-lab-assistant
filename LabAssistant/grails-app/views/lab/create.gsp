<head>
    <title>Create Lab | Lab Assistant</title>
    <meta name="layout" content="main" />
</head>
<body>
    <h2><g:message code="labs.create.heading" /></h2>
    <g:form name="newLab" url="[controller:'lab',action:'save']">
    <p><label for="name">Name</label>
    <g:textField name="name" /></p>
    <p><g:actionSubmit action="save" value="${message(code: 'labs.create.submit')}" /></p>
    </g:form>
</body>