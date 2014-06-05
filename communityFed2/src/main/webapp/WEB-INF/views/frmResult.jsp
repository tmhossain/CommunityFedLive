<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Home</title>
</head>
<body>
<h1>
	Submission Result
</h1>

<P>  The time on the server is ${serverTime}. </P>


Submission successful, submitted data </br>
${frm.firstName}
${frm.lastName}
</br>
<a href="${pageContext.request.contextPath}/">Back to Home</a>
<br/>

</body>


</html>
