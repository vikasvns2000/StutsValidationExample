<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
</head>
<body>

	<h2>Struts - Validator Example</h2>

	<b>User Registeration Form</b>
	<br />
	<br />

	<font color="red"> <html:errors />
	</font>

	<html:form action="/Register">

		<br />
		<bean:message key="label.user.username" /> : 
<html:text property="username" size="25" />
		<br />
		<bean:message key="label.user.password1" /> : 
<html:text property="password1" size="25" />
		<br />
		<bean:message key="label.user.password2" /> : 
<html:text property="password2" size="25" />
		<br />
		<bean:message key="label.user.email" /> : 
<html:text property="email" size="25" />
		<br />
		<br />
		<html:submit>
			<bean:message key="label.user.button.submit" />
		</html:submit>

	</html:form>

</body>
</html>