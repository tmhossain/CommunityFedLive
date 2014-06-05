<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ page session="true" %>
<html>
<head>
	<title>Home</title>
		<style>
.error {
	color: #ff0000;
}
 
.errorblock {
	color: #000;
	background-color: #ffEEEE;
	border: 3px solid #ff0000;
	padding: 8px;
	margin: 16px;
}
</style>
</head>
<body>
<h1>
	Violation entry form 
</h1>

 
<form:form method="post" action="${pageContext.request.contextPath}/fSubmit" commandName="userForm" >
	
    <form:label path="firstName">First Name</form:label>
    <form:input path="firstName" />
    <form:errors path="firstName" cssclass="errorblock"></form:errors>
    <br />
    <form:label path="lastName">Last Name</form:label>
    <form:input path="lastName" />
    <form:errors path="lastName" cssclass="errorblock"></form:errors>
    <br />
    <input type="submit" value="Submit" />
</form:form>
</body>
</html>
