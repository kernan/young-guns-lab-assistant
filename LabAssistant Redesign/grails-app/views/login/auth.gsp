<head>
<meta name='layout' content='main' />
<link rel='stylesheet' href='${resource(dir:"css", file:"login.css")}' />
<title>Sign in | Lab Assistant</title>
</head>

<body>
	<div id='login'>
		<div class='inner'>
			<g:if test='${flash.message}'>
			<div class='login_message'>${flash.message}</div>
			</g:if>
			<div class='fheader'><g:message code="auth.instructions" /></div>
			<form action='${postUrl}' method='POST' id='loginForm' class='cssform'>
				<p>
					<label for='username'><g:message code="auth.username" /></label>
					<input type='text' class='text_' name='j_username' id='username' />
				</p>
				<p>
					<label for='password'><g:message code="auth.password" /></label>
					<input type='password' class='text_' name='j_password' id='password' />
				</p>
				<!--<p>
					<label for='remember_me'>Remember me</label>
					<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me'
					<g:if test='${hasCookie}'>checked='checked'</g:if> />
				</p>-->
				<p>
					<input type='submit' value='${message(code:"auth.signin.button")}' />
				</p>
			</form>
		</div>
	</div>
<script type='text/javascript'>
<!--
(function(){
	document.forms['loginForm'].elements['j_username'].focus();
})();
// -->
</script>
</body>
